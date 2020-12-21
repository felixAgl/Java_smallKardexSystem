package com.felix.JavaSmallKardexSystem.product.service.impl;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.felix.JavaSmallKardexSystem.entity.Stock;
import com.felix.JavaSmallKardexSystem.product.repository.StockRepository;
import com.felix.JavaSmallKardexSystem.product.service.StockService;
import com.felix.JavaSmallKardexSystem.shared.enumeration.ProductError;
import com.felix.JavaSmallKardexSystem.shared.exception.ProductException;

@Service
public class StockServiceImpl implements StockService {

	private final StockRepository stockRepository;

	public StockServiceImpl(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	@Override
	public void saveProduct(Stock stock) throws ProductException {
		Optional<Stock> oIdStock = stockRepository.findById(stock.getId());
		Optional<Stock> oByName = stockRepository.findByName(stock.getName());
		Optional<Stock> oByDocument = stockRepository.findByDocument(stock.getDocument());
		if (oIdStock.isPresent() || oByName.isPresent()) {
			if (oByDocument.isPresent()) {
				throw new ProductException(ProductError.PRODUCT_ALREADY_EXIST.getMessage());
			}
			Stock stockDb = oIdStock.get();
			stock.setCost((stockDb.getTotal().add(stock.getTotal())
					.divide((stockDb.getQuantity().add(stock.getQuantity())))));
			stock.setQuantity(stockDb.getQuantity().add(stock.getQuantity()));
			stock.setTotal(stock.getQuantity().multiply(stock.getCost()));
		}
		stockRepository.save(stock);
	}

	@Override
	public Stock getProductById(Long id) throws ProductException {
		Optional<Stock> oProductFindById = stockRepository.findById(id);
		if (!oProductFindById.isPresent()) {
			throw new ProductException(ProductError.PRODUCT_NOT_FOUND.getMessage());
		}
		return oProductFindById.get();
	}

	@Override
	public void updateProduct(Stock stock, Long id) throws ProductException {
		Optional<Stock> oProductFindById = stockRepository.findById(id);
		if (stock == null || !oProductFindById.isPresent()) {
			throw new ProductException(ProductError.PRODUCT_NOT_FOUND.getMessage());
		}
		Optional<Stock> oByName = stockRepository.findByName(stock.getName());
		if (oByName.isPresent() && !stock.getName().equalsIgnoreCase(oProductFindById.get().getName())) {
			throw new ProductException(ProductError.PRODUCT_ALREADY_EXIST.getMessage());
		}
		stockRepository.save(stock);
	}

	@Override
	public void deleteProduct(Long id) throws ProductException {
		Optional<Stock> oProductFindById = stockRepository.findById(id);
		if (oProductFindById.get().getQuantity().compareTo(BigDecimal.ZERO) > 0) {
			throw new ProductException(ProductError.PRODUCTS_IN_STOCK.getMessage());
		}
		stockRepository.deleteById(id);
	}

	@Override
	public Iterable<Stock> getAllProducts() {
		return stockRepository.findAll();
	}

}

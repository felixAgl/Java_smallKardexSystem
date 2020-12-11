package com.felix.JavaSmallKardexSystem.product.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.felix.JavaSmallKardexSystem.entity.ProductSale;
import com.felix.JavaSmallKardexSystem.entity.Stock;
import com.felix.JavaSmallKardexSystem.product.repository.ProductSaleRepository;
import com.felix.JavaSmallKardexSystem.product.repository.StockRepository;
import com.felix.JavaSmallKardexSystem.product.service.ProductSaleService;
import com.felix.JavaSmallKardexSystem.shared.enumeration.ProductError;
import com.felix.JavaSmallKardexSystem.shared.exception.ProductException;

@Service
public class ProductSaleServiceImpl implements ProductSaleService {

	private final ProductSaleRepository productSaleRepository;
	private final StockRepository stockRepository;

	public ProductSaleServiceImpl(ProductSaleRepository productSaleRepository, StockRepository stockRepository) {
		this.productSaleRepository = productSaleRepository;
		this.stockRepository = stockRepository;
	}

	@Override
	public void saveSale(ProductSale productSale) throws ProductException {
		Optional<Stock> product = stockRepository.findById(productSale.getRefProduct().longValue());
		if (!product.isPresent()) {
			throw new ProductException(ProductError.PRODUCT_NOT_FOUND.getMessage());
		}
		if (product.get().getQuantity().longValue() < productSale.getQuantity().longValue()) {
			throw new ProductException(ProductError.NO_PRODUCTS_IN_STOCK.getMessage());
		}
		productSaleRepository.save(productSale);
		product.get().setQuantity(product.get().getQuantity().subtract(productSale.getQuantity()));
		product.get().setTotal(product.get().getQuantity().multiply(product.get().getCost()));
		stockRepository.save(product.get());
	}

}

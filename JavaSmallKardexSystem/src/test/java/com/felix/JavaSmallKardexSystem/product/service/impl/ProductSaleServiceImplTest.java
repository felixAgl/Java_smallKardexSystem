package com.felix.JavaSmallKardexSystem.product.service.impl;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.mockito.Mockito;

import com.felix.JavaSmallKardexSystem.entity.ProductSale;
import com.felix.JavaSmallKardexSystem.entity.Stock;
import com.felix.JavaSmallKardexSystem.product.repository.ProductSaleRepository;
import com.felix.JavaSmallKardexSystem.product.repository.StockRepository;
import com.felix.JavaSmallKardexSystem.shared.enumeration.ProductError;
import com.felix.JavaSmallKardexSystem.shared.exception.ProductException;

public class ProductSaleServiceImplTest {

	@Test
	public void saveSaleIsCorrect() {
		// Arrange
		Stock stock = new Stock(1, 1, "Camisas", 100, 5000, 500000, "Compra");
		ProductSale productSale = new ProductSale(1, 1, "Camisas", 50, 5000, 250000, "venta");
		ProductSaleRepository productSaleRepository = Mockito.mock(ProductSaleRepository.class);
		StockRepository stockRepository = Mockito.mock(StockRepository.class);
		Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stock));
		Mockito.when(productSaleRepository.save(Mockito.any(ProductSale.class))).thenReturn(productSale);
		Mockito.when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(stock);
		ProductSaleServiceImpl productSaleServiceImpl = new ProductSaleServiceImpl(productSaleRepository,
				stockRepository);
		// Act
		try {
			productSaleServiceImpl.saveSale(productSale);
		} catch (ProductException e) {
			e.printStackTrace();
		}
		// Assert
		Mockito.verify(stockRepository).findById(Mockito.anyLong());
		Mockito.verify(productSaleRepository).save(Mockito.any(ProductSale.class));
		Mockito.verify(stockRepository).save(Mockito.any(Stock.class));

	}

	@Test
	public void saveSaleIsIncorrectBecauseProductNotFound() {
		// Arrange
		ProductSale productSale = new ProductSale(1, 1, "Camisas", 50, 5000, 250000, "venta");
		ProductSaleRepository productSaleRepository = Mockito.mock(ProductSaleRepository.class);
		StockRepository stockRepository = Mockito.mock(StockRepository.class);
		Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		ProductSaleServiceImpl productSaleServiceImpl = new ProductSaleServiceImpl(productSaleRepository,
				stockRepository);
		try {
			// Act
			productSaleServiceImpl.saveSale(productSale);
			fail();
		} catch (ProductException e) {
			e.printStackTrace();
			// Assert
			assertEquals(ProductError.PRODUCT_NOT_FOUND.getMessage(), e.getMessage());
		}
		// Assert
		Mockito.verify(stockRepository).findById(Mockito.anyLong());
	}

	@Test
	public void saveSaleIsIncorrectBecauseNoStockAvailable() {
		// Arrange
		Stock stock = new Stock(1, 1, "Camisas", 100, 5000, 500000, "Compra");
		ProductSale productSale = new ProductSale(1, 1, "Camisas", 200, 5000, 1000000, "venta");
		ProductSaleRepository productSaleRepository = Mockito.mock(ProductSaleRepository.class);
		StockRepository stockRepository = Mockito.mock(StockRepository.class);
		Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stock));
		ProductSaleServiceImpl productSaleServiceImpl = new ProductSaleServiceImpl(productSaleRepository,
				stockRepository);
		try {
			// Act
			productSaleServiceImpl.saveSale(productSale);
			fail();
		} catch (ProductException e) {
			e.printStackTrace();
			// Assert
			assertEquals(ProductError.NO_PRODUCTS_IN_STOCK.getMessage(), e.getMessage());
		}
		// Assert
		Mockito.verify(stockRepository).findById(Mockito.anyLong());
	}

}

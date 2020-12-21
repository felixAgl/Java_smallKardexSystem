package com.felix.JavaSmallKardexSystem.product.service.impl;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.mockito.Mockito;

import com.felix.JavaSmallKardexSystem.entity.Stock;
import com.felix.JavaSmallKardexSystem.product.repository.StockRepository;
import com.felix.JavaSmallKardexSystem.product.service.StockService;
import com.felix.JavaSmallKardexSystem.shared.enumeration.ProductError;
import com.felix.JavaSmallKardexSystem.shared.exception.ProductException;

public class StockServiceImplTest {

	@Test
	public void saveSaleIsCorrect() {
		// Arrange
		Stock stock = new Stock(1, 1, "Camisas", 10, 1000, 100000, "Compra");
		StockRepository stockRepository = Mockito.mock(StockRepository.class);
		Mockito.when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(stock);
		Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stock));
		Mockito.when(stockRepository.findByName(Mockito.anyString())).thenReturn(Optional.of(stock));
		Mockito.when(stockRepository.findByDocument(Mockito.any())).thenReturn(Optional.empty());
		StockService stockService = new StockServiceImpl(stockRepository);
		try {
			// Act
			stockService.saveProduct(stock);
		} catch (ProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Assert
		Mockito.verify(stockRepository).save(Mockito.any(Stock.class));
		Mockito.verify(stockRepository).findById(Mockito.anyLong());
		Mockito.verify(stockRepository).findByDocument(Mockito.any());
	}

	@Test
	public void saveProductReturnProductAlreadyExist() {
		// Arrange
		Stock stock = new Stock(1, 1, "Camisas", 50, 5000, 250000, "Compra");
		StockRepository stockRepository = Mockito.mock(StockRepository.class);
		Mockito.when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(stock);
		Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stock));
		Mockito.when(stockRepository.findByName(Mockito.anyString())).thenReturn(Optional.of(stock));
		Mockito.when(stockRepository.findByDocument(Mockito.any())).thenReturn(Optional.of(stock));
		StockService stockService = new StockServiceImpl(stockRepository);
		try {
			// Act
			stockService.saveProduct(stock);
			fail();
		} catch (ProductException e) {
			e.printStackTrace();
			assertEquals(ProductError.PRODUCT_ALREADY_EXIST.getMessage(), e.getMessage());
		}
	}

	@Test
	public void getProductByIdIsCorrect() {
		// Arrange
		Stock stockExpected = new Stock(1, 1, "Camisas", 50, 5000, 250000, "Compra");
		StockRepository stockRepository = Mockito.mock(StockRepository.class);
		Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stockExpected));
		StockService stockService = new StockServiceImpl(stockRepository);
		try {
			// Act
			Stock stockActual = stockService.getProductById(stockExpected.getId());
			// Assert
			assertEquals(stockExpected.getId(), stockActual.getId());
			assertEquals(stockExpected.getName(), stockActual.getName());
			assertEquals(stockExpected.getDetail(), stockActual.getDetail());
		} catch (ProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void getProductByIdNotFound() {
		// Arrange
		Stock stockExpected = new Stock(1, 1, "Camisas", 50, 5000, 250000, "Compra");
		StockRepository stockRepository = Mockito.mock(StockRepository.class);
		Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		StockService stockService = new StockServiceImpl(stockRepository);
		try {
			// Act
			stockService.getProductById(stockExpected.getId());
			fail();
		} catch (ProductException e) {
			// Assert
			assertEquals(ProductError.PRODUCT_NOT_FOUND.getMessage(), e.getMessage());
		}
		Mockito.verify(stockRepository).findById(Mockito.anyLong());
	}

	@Test
	public void updateProductIsCorrect() {
		// Arrange
		Stock stock = new Stock(1, 1, "Camisas", 50, 5000, 250000, "Compra");
		Stock newStock = new Stock(1, 1, "Camisas", 50, 5000, 250000, "Compra");
		StockRepository stockRepository = Mockito.mock(StockRepository.class);
		Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stock));
		Mockito.when(stockRepository.findByName(Mockito.anyString())).thenReturn(Optional.empty());
		Mockito.when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(stock);
		StockService stockService = new StockServiceImpl(stockRepository);
		try {
			// Act
			stockService.updateProduct(newStock, stock.getId());
		} catch (ProductException e) {
			e.printStackTrace();
		}
		// Assert
		Mockito.verify(stockRepository).findById(Mockito.anyLong());
		Mockito.verify(stockRepository).findByName(Mockito.anyString());
		Mockito.verify(stockRepository).save(Mockito.any(Stock.class));
	}

	@Test
	public void updateProductIsIncorrectBecauseObjectIsNull() {
		// Arrange
		Stock stock = new Stock(1, 1, "Camisas", 50, 5000, 250000, "Compra");
		Stock nullStock = null;
		StockRepository stockRepository = Mockito.mock(StockRepository.class);
		Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stock));
		StockService stockService = new StockServiceImpl(stockRepository);
		try {
			// Act
			stockService.updateProduct(nullStock, stock.getId());
			fail();
		} catch (ProductException e) {
			// Assert
			assertEquals(ProductError.PRODUCT_NOT_FOUND.getMessage(), e.getMessage());
		}
		// Assert
		Mockito.verify(stockRepository).findById(Mockito.anyLong());
	}

	@Test
	public void updateProductIsIncorrectBecauseIdDoesNotExist() {
		// Arrange
		Stock stock = new Stock(1, 1, "Camisas", 50, 5000, 250000, "Compra");
		Stock newStock = new Stock(1, 1, "Camisas Ironman", 50, 5000, 250000, "Compra");
		StockRepository stockRepository = Mockito.mock(StockRepository.class);
		Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		StockService stockService = new StockServiceImpl(stockRepository);
		try {
			// Act
			stockService.updateProduct(newStock, stock.getId());
			fail();
		} catch (ProductException e) {
			// Assert
			assertEquals(ProductError.PRODUCT_NOT_FOUND.getMessage(), e.getMessage());
		}
		// Assert
		Mockito.verify(stockRepository).findById(Mockito.anyLong());
	}

	@Test
	public void updateProductIsIncorrectBecauseProductNameEqualsOtherProduct() {
		// Arrange
		Stock stock1 = new Stock(1, 1, "Camisas", 50, 5000, 250000, "Compra");
		Stock stock2 = new Stock(2, 2, "Camisas Ironman", 50, 5000, 250000, "Compra");
		Stock newNameStock1 = new Stock(1, 1, "Camisas Ironman", 50, 5000, 250000, "Compra");
		StockRepository stockRepository = Mockito.mock(StockRepository.class);
		Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stock1));
		Mockito.when(stockRepository.findByName(Mockito.anyString())).thenReturn(Optional.of(stock2));
		StockService stockService = new StockServiceImpl(stockRepository);
		try {
			// Act
			stockService.updateProduct(newNameStock1, stock1.getId());
			fail();
		} catch (ProductException e) {
			// Assert
			assertEquals(ProductError.PRODUCT_ALREADY_EXIST.getMessage(), e.getMessage());
		}
		// Assert
		Mockito.verify(stockRepository).findById(Mockito.anyLong());
		Mockito.verify(stockRepository).findByName(Mockito.anyString());
	}

	@Test
	public void deleteProductIsCorrect() {
		// Arrange
		Stock stock = new Stock(1, 1, "Camisas", 0, 5000, 250000, "Compra");
		StockRepository stockRepository = Mockito.mock(StockRepository.class);
		Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stock));
		Mockito.doNothing().when(stockRepository).deleteById(Mockito.anyLong());
		StockService stockService = new StockServiceImpl(stockRepository);
		try {
			// Act
			stockService.deleteProduct(stock.getId());
		} catch (ProductException e) {
			e.printStackTrace();
		}
		// Assert
		Mockito.verify(stockRepository).findById(Mockito.anyLong());
		Mockito.verify(stockRepository).deleteById(Mockito.anyLong());
	}

	@Test
	public void deleteProductIsIncorrect() {
		// Arrange
		Stock stock = new Stock(1, 1, "Camisas", 50, 5000, 250000, "Compra");
		StockRepository stockRepository = Mockito.mock(StockRepository.class);
		Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stock));
		StockService stockService = new StockServiceImpl(stockRepository);
		try {
			// Act
			stockService.deleteProduct(stock.getId());
			fail();
		} catch (ProductException e) {
			// Assert
			assertEquals(ProductError.PRODUCTS_IN_STOCK.getMessage(), e.getMessage());
		}
		// Assert
		Mockito.verify(stockRepository).findById(Mockito.anyLong());
	}

	@Test
	public void getAllProductIsCorrect() {
		// Arrange
		Stock stock1 = new Stock(1, 1, "Camisas", 50, 5000, 250000, "Compra");
		Stock stock2 = new Stock(2, 2, "Figuras", 50, 5000, 250000, "Compra");
		Stock stock3 = new Stock(3, 3, "Comics", 50, 5000, 250000, "Compra");
		List<Stock> stockList = Arrays.asList(stock1, stock2, stock3);
		StockRepository stockRepository = Mockito.mock(StockRepository.class);
		Mockito.when(stockRepository.findAll()).thenReturn(stockList);
		StockService stockService = new StockServiceImpl(stockRepository);
		// Act
		Iterable<Stock> stockListActual = stockService.getAllProducts();
		// Assert
		assertEquals(stockList, stockListActual);
		Mockito.verify(stockRepository).findAll();
	}

}

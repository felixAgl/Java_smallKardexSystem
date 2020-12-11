package com.felix.JavaSmallKardexSystem.product.service;

import com.felix.JavaSmallKardexSystem.entity.Stock;
import com.felix.JavaSmallKardexSystem.shared.exception.ProductException;

public interface StockService {

	void saveProduct(Stock stock) throws ProductException;

	Stock getProductById(Long id) throws ProductException;;

	void updateProduct(Stock stock, Long id) throws ProductException;

	void deleteProduct(Long id) throws ProductException;

	Iterable<Stock> getAllProducts();

}

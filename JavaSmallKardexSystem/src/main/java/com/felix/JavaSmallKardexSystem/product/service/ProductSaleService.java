package com.felix.JavaSmallKardexSystem.product.service;

import com.felix.JavaSmallKardexSystem.entity.ProductSale;
import com.felix.JavaSmallKardexSystem.shared.exception.ProductException;

public interface ProductSaleService {

	void saveSale(ProductSale productSale) throws ProductException ;

}

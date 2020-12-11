package com.felix.JavaSmallKardexSystem.product.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felix.JavaSmallKardexSystem.entity.ProductSale;
import com.felix.JavaSmallKardexSystem.product.service.ProductSaleService;
import com.felix.JavaSmallKardexSystem.shared.exception.ProductException;

@RestController
@RequestMapping("sale")
public class ProductSaleController {

	private final ProductSaleService productSaleService;

	public ProductSaleController(ProductSaleService productSaleService) {
		this.productSaleService = productSaleService;
	}

	@PostMapping("/")
	public void saveSale(@RequestBody ProductSale productSale) throws ProductException {
		productSaleService.saveSale(productSale);

	}
}

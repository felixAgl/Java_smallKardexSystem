package com.felix.JavaSmallKardexSystem.product.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felix.JavaSmallKardexSystem.entity.Stock;
import com.felix.JavaSmallKardexSystem.product.service.StockService;
import com.felix.JavaSmallKardexSystem.shared.exception.ProductException;

@RestController
@RequestMapping("products")
public class StockController {

	private final StockService stockService;

	public StockController(StockService stockService) {
		this.stockService = stockService;
	}

	@PostMapping("/")
	public void saveProduct(@RequestBody Stock stock) throws ProductException {
		stockService.saveProduct(stock);
	}

	@GetMapping("/{id}")
	public Stock getProfuctById(@PathVariable Long id) throws ProductException {
		return stockService.getProductById(id);
	}

	@PutMapping("/{id}")
	public void updateProduct(@PathVariable Long id, @RequestBody Stock stock) throws ProductException {
		stockService.updateProduct(stock, id);
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) throws ProductException {
		stockService.deleteProduct(id);
	}

	@GetMapping("/")
	public Iterable<Stock> getAllProducts() {
		return stockService.getAllProducts();
	}
}

package com.felix.JavaSmallKardexSystem.shared.exception;

import com.felix.JavaSmallKardexSystem.shared.enumeration.ProductError;

public class ProductException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;
	private ProductError productError;

	public ProductException(String message) {
		this.message = message;
	}

	public ProductException(ProductError productError) {
		this.productError = productError;
	}

	public String getMessage() {
		return message;
	}

	public ProductError getProductError() {
		return productError;
	}

}

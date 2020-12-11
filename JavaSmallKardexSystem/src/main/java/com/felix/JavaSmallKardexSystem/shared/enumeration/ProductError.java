package com.felix.JavaSmallKardexSystem.shared.enumeration;

public enum ProductError {

	PRODUCT_NOT_FOUND("Stock not found"), NO_PRODUCTS_IN_STOCK("There are not enough products in stock"),
	PRODUCT_ALREADY_EXIST("Product already exist!"), PRODUCTS_IN_STOCK("Cannot be removed. There are units in stock."), VALIDATE_DATA("Validate input data");

	private final String message;

	private ProductError(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}

package com.felix.JavaSmallKardexSystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSale {

	@Id
	private Long id; // document
	@Column
	private Long refProduct; // id product
	@Column
	private String name;
	@Column
	private Long quantity;
	@Column
	private Long cost;
	@Column
	private Long total;
	@Column
	private String detail;

}

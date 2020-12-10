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
public class Stock {

	@Id
	private Long id;
	@Column
	private Long document;
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

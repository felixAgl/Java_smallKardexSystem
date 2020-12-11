package com.felix.JavaSmallKardexSystem.product.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.felix.JavaSmallKardexSystem.entity.Stock;

@Repository
public interface StockRepository extends CrudRepository<Stock, Long> {

	Optional<Stock> findByName(String name);

	Optional<Stock> findByDocument(BigDecimal document);

}

package com.jdc.security.demo.model.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private double price;

	@Enumerated(EnumType.STRING)
	private Currency currency;

	public enum Currency {
		USD, GBP, EUR
	}

	public Product(String name, double price, Currency currency) {
		super();
		this.name = name;
		this.price = price;
		this.currency = currency;
	}

}

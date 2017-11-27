package com.sg.calculator.domain;

public class Product {

	private Integer id;
	private String brandName;
	private String category;
	private Integer price;
	private Integer discountedPrice;

	public Product(Integer id, String brandName, String category, Integer price) {
		super();
		this.id = id;
		this.brandName = brandName;
		this.category = category;
		this.price = price;
	}

	public Product(Integer id, String brandName, String category, Integer price, Integer discountedPrice) {
		super();
		this.id = id;
		this.brandName = brandName;
		this.category = category;
		this.price = price;
		this.discountedPrice = discountedPrice;
	}

	public Integer getId() {
		return id;
	}

	public String getBrandName() {
		return brandName;
	}

	public String getCategory() {
		return category;
	}

	public Integer getPrice() {
		return price;
	}

	public Integer getDiscountedPrice() {
		return discountedPrice;
	}

}

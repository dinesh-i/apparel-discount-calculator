package com.sg.calculator.util;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.sg.calculator.DiscountCalculator;
import com.sg.calculator.domain.Product;
import com.sg.calculator.reader.BrandDiscountReader;
import com.sg.calculator.reader.CatalogueReader;
import com.sg.calculator.reader.CategoriesReader;
import com.sg.calculator.reader.OrderReader;

public class DiscountCalculatorTest {

	private List<Product> products;
	private Node categories;
	List<List<Integer>> orders;
	private Map<String, Integer> brandDiscounts;

	private CatalogueReader catalogueReader;
	private OrderReader orderReader;
	private CategoriesReader categoriesReader;
	private BrandDiscountReader brandDiscountReader;

	private DiscountCalculator discountCalculator;

	@Before
	public void setUp() throws Exception {

		catalogueReader = new CatalogueReader();
		orderReader = new OrderReader();
		categoriesReader = new CategoriesReader();
		brandDiscountReader = new BrandDiscountReader();

		products = catalogueReader.read("src/test/resources/product-catalogue.csv");
		orders = orderReader.read("src/test/resources/order.csv");
		categories = categoriesReader.read("src/main/resources/categories-and-discounts.csv");
		brandDiscounts = brandDiscountReader.read("src/main/resources/brands-and-discounts.csv");

		discountCalculator = new DiscountCalculator();
	}

	// @Ignore
	@Test
	public void test() {
		List<Integer> discountedPriceValues = discountCalculator.getDiscountedPrices(orders, products, categories, brandDiscounts);
		assertEquals(560, discountedPriceValues.get(0).intValue());
		assertEquals(3860, discountedPriceValues.get(1).intValue());
		assertEquals(2140, discountedPriceValues.get(2).intValue());

		System.out.println(discountedPriceValues);
	}

}

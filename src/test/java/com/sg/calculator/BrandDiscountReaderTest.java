package com.sg.calculator;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.sg.calculator.reader.BrandDiscountReader;

public class BrandDiscountReaderTest {

	private Map<String, Integer> brandDiscounts;
	private BrandDiscountReader brandDiscountReader;

	@Before
	public void setUp() throws Exception {
		brandDiscountReader = new BrandDiscountReader();
		brandDiscounts = brandDiscountReader.read("src/main/resources/brands-and-discounts.csv");
	}

	@Test
	public void shouldMatchTheBrandAndAssociatedDiscount() {
		assertEquals(10, brandDiscounts.get("Wrangler").intValue());
		assertEquals(20, brandDiscounts.get("Arrow").intValue());
		assertEquals(60, brandDiscounts.get("Vero Moda").intValue());
		assertEquals(0, brandDiscounts.get("UCB").intValue());
		assertEquals(5, brandDiscounts.get("Adidas").intValue());
		assertEquals(20, brandDiscounts.get("Provogue").intValue());
	}

}

package com.sg.calculator.util;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.sg.calculator.reader.BrandDiscountReader;

public class BrandDiscountReaderTest {

	private Map<String, Integer> brandDiscounts;

	@Before
	public void setUp() throws Exception {
		brandDiscounts = BrandDiscountReader.read("src/main/resources/brands-and-discounts.csv");
	}

	@Test
	public void shouldMatchTheBrandAndAssoicatedDiscount() {
		assertEquals(10, brandDiscounts.get("Wrangler").intValue());
		assertEquals(20, brandDiscounts.get("Arrow").intValue());
		assertEquals(60, brandDiscounts.get("Vero Moda").intValue());
		assertEquals(0, brandDiscounts.get("UCB").intValue());
		assertEquals(5, brandDiscounts.get("Adidas").intValue());
		assertEquals(20, brandDiscounts.get("Provogue").intValue());
	}

}

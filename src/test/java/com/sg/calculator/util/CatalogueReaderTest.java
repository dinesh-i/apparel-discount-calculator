package com.sg.calculator.util;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.sg.calculator.domain.Product;
import com.sg.calculator.reader.CatalogueReader;

public class CatalogueReaderTest {

	private List<Product> products;

	@Before
	public void setUp() throws Exception {

		products = CatalogueReader.read("src/test/resources/product-catalogue.csv");

	}

	@Test
	public void test() {
		assertEquals(5, products.size());
		assertEquals(1, products.get(0).getId().intValue());
		assertEquals("UCB", products.get(4).getBrandName());
	}

}
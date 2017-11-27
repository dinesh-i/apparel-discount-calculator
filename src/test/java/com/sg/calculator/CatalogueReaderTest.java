package com.sg.calculator;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.sg.calculator.domain.Product;
import com.sg.calculator.reader.CatalogueReader;

public class CatalogueReaderTest {

	private List<Product> products;
	private CatalogueReader catalogueReader;

	@Before
	public void setUp() throws Exception {
		catalogueReader = new CatalogueReader();
		products = catalogueReader.read("src/test/resources/product-catalogue.csv");

	}

	@Test
	public void shouldReadProducts() {
		assertEquals(5, products.size());
		assertEquals(1, products.get(0).getId().intValue());
		assertEquals("UCB", products.get(4).getBrandName());
	}

}

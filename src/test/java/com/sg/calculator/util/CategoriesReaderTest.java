package com.sg.calculator.util;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.sg.calculator.reader.CategoriesReader;

public class CategoriesReaderTest {

	private Node categories;

	@Before
	public void setUp() throws Exception {
		categories = CategoriesReader.read("src/main/resources/categories-and-discounts.csv");
	}

	@Test
	public void shouldReadAndConstructTheHierarchyOfCategories() {
		Set<Node> menAndWomensWear = categories.getChildren();
		assertEquals(2, menAndWomensWear.size());
	}

}

package com.sg.calculator;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.sg.calculator.reader.CategoriesReader;
import com.sg.calculator.util.Node;

public class CategoriesReaderTest {

	private Node categories;
	private CategoriesReader categoriesReader;

	@Before
	public void setUp() throws Exception {
		categoriesReader = new CategoriesReader();
		categories = categoriesReader.read("src/main/resources/categories-and-discounts.csv");
	}

	@Test
	public void shouldReadAndConstructTheHierarchyOfCategories() {
		Set<Node> menAndWomensWear = categories.getChildren();
		assertEquals(2, menAndWomensWear.size());
	}

}

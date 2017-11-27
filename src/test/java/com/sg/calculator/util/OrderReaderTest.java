package com.sg.calculator.util;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.sg.calculator.reader.OrderReader;

public class OrderReaderTest {

	List<List<Integer>> result;

	@Before
	public void setUp() throws Exception {
		result = OrderReader.read("src/test/resources/order.csv");
	}

	@Test
	public void test() {
		assertEquals(3, result.size());
		assertEquals(1, result.get(0).size());
		assertEquals(4, result.get(1).size());
		assertEquals(5, result.get(2).get(1).intValue());
	}

}

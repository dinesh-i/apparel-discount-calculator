package com.sg.calculator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BrandDiscountReaderTest.class, CatalogueReaderTest.class, CategoriesReaderTest.class, DiscountCalculatorTest.class, NodeTest.class,
		OrderReaderTest.class, TreeUtilTest.class })
public class AllTests {

}

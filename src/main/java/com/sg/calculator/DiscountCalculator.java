package com.sg.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sg.calculator.domain.Product;
import com.sg.calculator.reader.BrandDiscountReader;
import com.sg.calculator.reader.CatalogueReader;
import com.sg.calculator.reader.CategoriesReader;
import com.sg.calculator.reader.OrderReader;
import com.sg.calculator.util.Node;
import com.sg.calculator.util.TreeUtil;

@Component
public class DiscountCalculator {

	BrandDiscountReader brandDiscountReader;
	CatalogueReader catalogueReader;
	CategoriesReader categoriesReader;
	OrderReader orderReader;

	@Autowired
	public void setBrandDiscountReader(BrandDiscountReader brandDiscountReader) {
		this.brandDiscountReader = brandDiscountReader;
	}

	@Autowired
	public void setCatalogueReader(CatalogueReader catalogueReader) {
		this.catalogueReader = catalogueReader;
	}

	@Autowired
	public void setCategoriesReader(CategoriesReader categoriesReader) {
		this.categoriesReader = categoriesReader;
	}

	@Autowired
	public void setOrderReader(OrderReader orderReader) {
		this.orderReader = orderReader;
	}

	public List<Integer> getDiscountedPrices(List<List<Integer>> orders, List<Product> products, Node categories, Map<String, Integer> brandDiscounts) {

		List<Integer> discountedOrderPrices = new ArrayList<>();

		for (List<Integer> order : orders) {
			int sumOfDiscountedOrderPrice = 0;
			for (Integer productId : order) {
				Product product = getProduct(productId, products);
				if (null == product)
					throw new RuntimeException("Product doesn't exist");

				// Compute brand discount
				int brandDiscount = 0, categoryDiscount = 0, maxAncestorCategoryDiscount = 0;
				if (null != brandDiscounts.get(product.getBrandName()))
					brandDiscount = brandDiscounts.get(product.getBrandName()).intValue();

				// Compute category and ancestor discounts
				Node category = TreeUtil.getNode(product.getCategory(), categories);
				if (null != category) {
					categoryDiscount = category.getDiscountPercentage();

					Node ancestorSearchNode = category;
					while (null != ancestorSearchNode.getParent()) {
						if (maxAncestorCategoryDiscount < ancestorSearchNode.getParent().getDiscountPercentage()) {
							maxAncestorCategoryDiscount = ancestorSearchNode.getParent().getDiscountPercentage();
						}
						ancestorSearchNode = ancestorSearchNode.getParent();
					}

				}

				int maxDiscount = Math.max(Math.max(brandDiscount, categoryDiscount), maxAncestorCategoryDiscount);
				Float discountAmount = product.getPrice() * ((float) maxDiscount / 100);
				int discountedPrice = product.getPrice() - discountAmount.intValue();

				sumOfDiscountedOrderPrice += discountedPrice;

			}
			discountedOrderPrices.add(sumOfDiscountedOrderPrice);

		}

		return discountedOrderPrices;
	}

	private Product getProduct(Integer productId, List<Product> products) {
		for (Product product : products) {
			if (product.getId().compareTo(productId) == 0)
				return product;
		}
		return null;
	}

	public List<Integer> getDiscountPrices(String orderFile, String productCatalogueFile, String categoriesAndDiscountsFile, String brandsAndDiscountsFile) {

		List<List<Integer>> orders = orderReader.read(orderFile);
		List<Product> products = catalogueReader.read(productCatalogueFile);
		Node categories = categoriesReader.read(categoriesAndDiscountsFile);
		Map<String, Integer> brandDiscounts = brandDiscountReader.read(brandsAndDiscountsFile);

		return getDiscountedPrices(orders, products, categories, brandDiscounts);

	}

}

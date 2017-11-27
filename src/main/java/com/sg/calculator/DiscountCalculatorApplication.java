package com.sg.calculator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.springframework.util.CollectionUtils;

public class DiscountCalculatorApplication {

	private static final String DEFAULT_BRANDS_AND_DISCOUNTS_FILE = "src/main/resources/brands-and-discounts.csv";
	private static final String DEFAULT_CATEGORIES_AND_DISCOUNTS_FILE = "src/main/resources/categories-and-discounts.csv";
	private static final String DEFAULT_DISCOUNTED_PRICES_FILE = "discounted-prices.txt";

	public static void main(String[] args) {
		Options options = new Options();

		Option orderFilePath = new Option("o", "orders", true, "Order file");
		orderFilePath.setRequired(true);
		options.addOption(orderFilePath);

		Option productCatalogueFilePath = new Option("p", "products", true, "Product catalogue File");
		productCatalogueFilePath.setRequired(true);
		options.addOption(productCatalogueFilePath);

		Option categoriesFilePath = new Option("c", "categories-and-discounts", true, "Categories and discounts File");
		categoriesFilePath.setRequired(false);
		options.addOption(categoriesFilePath);

		Option brandFilePath = new Option("b", "brands-and-discounts", true, "Brands and discounts file");
		brandFilePath.setRequired(false);
		options.addOption(brandFilePath);

		Option outputFilePath = new Option("o", "output", true, "Output file with the discounted prices");
		outputFilePath.setRequired(false);
		options.addOption(outputFilePath);

		Option overwrite = new Option("f", "overwrite-output-file-if-required", false, "Overwrite output file if it already exists");
		overwrite.setRequired(false);
		options.addOption(overwrite);

		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd;

		try {
			CommandLineParser parser = new DefaultParser();
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			formatter.printHelp("discount-calculator", options);

			System.exit(1);
			return;
		}

		String orderFile = cmd.getOptionValue("orders");
		String productCatalogueFile = cmd.getOptionValue("products");
		String categoriesAndDiscountsFile = cmd.getOptionValue("categories-and-discounts");
		if (null == categoriesAndDiscountsFile)
			categoriesAndDiscountsFile = DEFAULT_CATEGORIES_AND_DISCOUNTS_FILE;

		String brandsAndDiscountsFile = cmd.getOptionValue("brands-and-discounts");
		if (null == brandsAndDiscountsFile)
			brandsAndDiscountsFile = DEFAULT_BRANDS_AND_DISCOUNTS_FILE;

		String outputFileName = cmd.getOptionValue("output");
		if (null == outputFileName)
			outputFileName = DEFAULT_DISCOUNTED_PRICES_FILE;

		boolean overwriteOutputFile = (null != overwrite) ? true : false;

		File outputFile = new File(outputFileName);
		if (outputFile.exists() && !overwriteOutputFile) {
			System.out.println(String.format("output file[%s] exists already. Please set the -f flag to overwrite the output file.", outputFileName));

			System.exit(1);
			return;
		}

		List<Integer> discountPrices = DiscountCalculator.getDiscountPrices(orderFile, productCatalogueFile, categoriesAndDiscountsFile,
				brandsAndDiscountsFile);

		if (!CollectionUtils.isEmpty(discountPrices))
			for (Integer discountedPrice : discountPrices) {
				System.out.println(discountedPrice);
			}

		try (FileWriter writer = new FileWriter(outputFile, false); BufferedWriter out = new BufferedWriter(writer);) {
			if (!CollectionUtils.isEmpty(discountPrices))
				for (Integer discountedPrice : discountPrices) {
					out.write(discountedPrice);
					out.newLine();

				}
		} catch (IOException e) {
			System.out.println(String.format("Exception[%s] in writing the output to the file.", e.getMessage()));
			e.printStackTrace();

			System.exit(1);
			return;
		}

	}
}

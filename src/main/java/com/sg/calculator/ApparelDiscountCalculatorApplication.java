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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.CollectionUtils;

@SpringBootApplication
public class ApparelDiscountCalculatorApplication {

	private static final String DEFAULT_BRANDS_AND_DISCOUNTS_FILE = "src/main/resources/brands-and-discounts.csv";
	private static final String DEFAULT_CATEGORIES_AND_DISCOUNTS_FILE = "src/main/resources/categories-and-discounts.csv";
	private static final String DEFAULT_DISCOUNTED_PRICES_FILE = "discounted-prices.txt";
	private static final String OVERWRITE_OUTPUT_FILE_SHORT_NOTATION = "f";

	public static void main(String[] args) {
		SpringApplication.run(ApparelDiscountCalculatorApplication.class, args);

		// Define the options
		Options options = getOptions();

		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd;

		// Parse the input arguments. Print usage in case of error
		try {
			CommandLineParser parser = new DefaultParser();
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			formatter.printHelp("discount-calculator", options);

			System.exit(1);
			return;
		}

		// Read the command line argument values to local variables. Set default values
		// for optional arguments like brand discount file and category discount file
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

		boolean overwriteOutputFile = cmd.hasOption(OVERWRITE_OUTPUT_FILE_SHORT_NOTATION);

		// If output file already exists and the overwrite flag is not set, then print
		// error message and exit
		File outputFile = new File(outputFileName);
		if (outputFile.exists() && !overwriteOutputFile) {
			System.out.println(String.format("output file[%s] exists already. Please set the -f flag to overwrite the output file.", outputFileName));

			System.exit(1);
			return;
		}

		// Calculate the discount price
		List<Integer> discountPrices = DiscountCalculator.getDiscountPrices(orderFile, productCatalogueFile, categoriesAndDiscountsFile,
				brandsAndDiscountsFile);

		// Print the discount price in console
		if (!CollectionUtils.isEmpty(discountPrices))
			for (Integer discountedPrice : discountPrices) {
				System.out.println(discountedPrice);
			}

		// Print the discount price to log file
		try (FileWriter writer = new FileWriter(outputFile, false); BufferedWriter out = new BufferedWriter(writer);) {
			if (!CollectionUtils.isEmpty(discountPrices))
				for (Integer discountedPrice : discountPrices) {
					out.write(String.valueOf(discountedPrice));
					out.newLine();

				}
		} catch (IOException e) {
			System.out.println(String.format("Exception[%s] in writing the output to the file.", e.getMessage()));
			e.printStackTrace();

			System.exit(1);
			return;
		}

	}

	private static Options getOptions() {
		Options options = new Options();

		Option orderFilePath = getOption("o", "orders", true, "Order file", true);
		options.addOption(orderFilePath);

		Option productCatalogueFilePath = getOption("p", "products", true, "Product catalogue File", true);
		options.addOption(productCatalogueFilePath);

		Option categoriesFilePath = getOption("c", "categories-and-discounts", true,
				"Categories and discounts File. If this field is not provided then default category discount file will be used.", false);
		options.addOption(categoriesFilePath);

		Option brandFilePath = getOption("b", "brands-and-discounts", true,
				"Brands and discounts file. If this field is not provided then default brand discount file will be used.", false);
		options.addOption(brandFilePath);

		Option outputFilePath = getOption("t", "target-file", true,
				"Output file with the discounted prices. If this field is not provided then defaults to a file named discounted-prices.txt in current directory",
				false);
		options.addOption(outputFilePath);

		Option overwrite = getOption(OVERWRITE_OUTPUT_FILE_SHORT_NOTATION, "overwrite-output-file", false, "Overwrite output file if it already exists", false);
		options.addOption(overwrite);
		return options;
	}

	private static Option getOption(String shortOption, String longOption, boolean hasArg, String description, boolean isRequired) {
		Option overwrite = new Option(shortOption, longOption, hasArg, description);
		overwrite.setRequired(isRequired);
		return overwrite;
	}
}

package com.sg.calculator.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sg.calculator.domain.Product;

public class CatalogueReader {

	public static List<Product> read(String catalogueFileName) {

		try (Stream<String> stream = Files.lines(Paths.get(catalogueFileName)).skip(1)) {

			// stream.forEach(System.out::println);

			List<Product> result = stream.map(line ->
			// {
			// String[] productElements = line.split(",");
			// new Product(Integer.parseInt(productElements[0], productElements[1],
			// productElements[2], Integer.parseInt(productElements[3]);
			new Product(Integer.parseInt(line.split(",")[0]), line.split(",")[1].trim(), line.split(",")[2].trim(), Integer.parseInt(line.split(",")[3]))
			// }
			).collect(Collectors.toList());

			return result;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}

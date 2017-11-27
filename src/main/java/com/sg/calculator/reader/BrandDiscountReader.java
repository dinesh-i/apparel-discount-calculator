package com.sg.calculator.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BrandDiscountReader {

	public static Map<String, Integer> read(String fileName) {
		try (Stream<String> stream = Files.lines(Paths.get(fileName)).filter(line -> !line.startsWith("#"))) {
			Map<String, Integer> result = stream.map(line -> line.split(","))
					.collect(Collectors.toMap(splittedStr -> splittedStr[0], splittedStr -> Integer.parseInt(splittedStr[1])));

			return result;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}

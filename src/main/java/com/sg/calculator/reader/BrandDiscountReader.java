package com.sg.calculator.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrandDiscountReader {

	private static final Logger logger = LoggerFactory.getLogger(BrandDiscountReader.class);

	public static Map<String, Integer> read(String fileName) {
		try (Stream<String> stream = Files.lines(Paths.get(fileName)).filter(line -> !line.startsWith("#"))) {
			Map<String, Integer> result = stream.map(line -> line.split(","))
					.collect(Collectors.toMap(splittedStr -> splittedStr[0], splittedStr -> Integer.parseInt(splittedStr[1])));

			return result;

		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Exception[%s] in reading the brand discount file", e.getMessage());
			throw new RuntimeException(e);
		}

	}

}

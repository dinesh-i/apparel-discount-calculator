package com.sg.calculator.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderReader implements Reader {

	private static final Logger logger = LoggerFactory.getLogger(BrandDiscountReader.class);

	@Override
	public List<List<Integer>> read(String orderFileName) {

		try (Stream<String> stream = Files.lines(Paths.get(orderFileName)).skip(1)) {

			List<List<Integer>> result = stream.map(line -> Arrays.asList(line.split(",")))
					.map(stringList -> stringList.stream().map(Integer::parseInt).collect(Collectors.toList())).collect(Collectors.toList());

			return result;

		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Exception[%s] in reading the order file", e.getMessage());
			throw new RuntimeException(e);
		}

	}

}

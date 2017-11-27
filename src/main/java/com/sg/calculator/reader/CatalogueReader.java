package com.sg.calculator.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sg.calculator.domain.Product;

@Component
public class CatalogueReader implements Reader {

	private static final Logger logger = LoggerFactory.getLogger(CatalogueReader.class);

	@Override
	public List<Product> read(String catalogueFileName) {

		try (Stream<String> stream = Files.lines(Paths.get(catalogueFileName)).skip(1)) {

			List<Product> result = stream.map(line -> new Product(Integer.parseInt(line.split(",")[0]), line.split(",")[1].trim(), line.split(",")[2].trim(),
					Integer.parseInt(line.split(",")[3]))).collect(Collectors.toList());

			return result;

		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Exception[%s] in reading the catalogue file", e.getMessage());
			throw new RuntimeException(e);
		}

	}

}

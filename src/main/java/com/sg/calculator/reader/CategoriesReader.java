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

import com.sg.calculator.util.Node;
import com.sg.calculator.util.TreeUtil;

public class CategoriesReader {

	private static final Logger logger = LoggerFactory.getLogger(CategoriesReader.class);

	public static Node read(String categoriesFileName) {

		Node rootNode = new Node(0, "Root", null, 0);
		TreeUtil treeUtil = new TreeUtil(rootNode);

		try (Stream<String> stream = Files.lines(Paths.get(categoriesFileName)).filter(line -> !line.startsWith("#"))) {
			List<Node> result = stream.map(line -> Arrays.asList(line.split(","))).map(stringList -> treeUtil.createNode(stringList))
					.collect(Collectors.toList());
			logger.debug("construced the node tree [%s]", result);

			return rootNode;

		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Exception[%s] in reading the categories file", e.getMessage());
			throw new RuntimeException(e);
		}

	}

}

package com.sg.calculator.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sg.calculator.util.Node;
import com.sg.calculator.util.TreeUtil;

public class CategoriesReader {

	public static Node read(String categoriesFileName) {

		Node rootNode = new Node(0, "Root", null, 0);
		TreeUtil treeUtil = new TreeUtil(rootNode);

		try (Stream<String> stream = Files.lines(Paths.get(categoriesFileName)).filter(line -> !line.startsWith("#"))) {
			// TODO: Read only the root node. Change the below logic
			List<Node> result = stream.map(line -> Arrays.asList(line.split(","))).map(stringList -> treeUtil.createNode(stringList))
					.collect(Collectors.toList());

			return rootNode;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}

package com.sg.calculator.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class NodeTest {

	Node mensNode, shirtsNode, trousersNode, casualsNode, womensNode, rootNode;

	@Before
	public void setUp() throws Exception {
		rootNode = new Node(0, "Root", null, 0);
		mensNode = new Node(1, "Men's Wear", rootNode, 0);

		shirtsNode = new Node(2, "Shirts", mensNode, 0);
		trousersNode = new Node(3, "Trousers", mensNode, 0);
		casualsNode = new Node(4, "Casuals", trousersNode, 30);
		womensNode = new Node(6, "Women's wear", rootNode, 50);

	}

	@Test
	public void shouldBuildTheHierarchyWithParentAndChildren() {
		assertEquals(2, rootNode.getChildren().size());
		assertEquals(2, mensNode.getChildren().size());
		assertEquals(0, womensNode.getParent().getId());
	}

}

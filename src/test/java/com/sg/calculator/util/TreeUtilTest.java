package com.sg.calculator.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TreeUtilTest {

	Node mensNode, shirtsNode, trousersNode, casualsNode, womensNode, rootNode;
	TreeUtil treeUtil;

	@Before
	public void setUp() throws Exception {
		rootNode = new Node(0, "Root", null, 0);
		mensNode = new Node(1, "Men's Wear", rootNode, 0);

		shirtsNode = new Node(2, "Shirts", mensNode, 0);
		trousersNode = new Node(3, "Trousers", mensNode, 0);
		casualsNode = new Node(4, "Casuals", trousersNode, 30);
		womensNode = new Node(6, "Women's wear", rootNode, 50);

		treeUtil = new TreeUtil(rootNode);

	}

	@Test
	public void test() {
		assertEquals(rootNode, treeUtil.getNode(0, rootNode));
		assertEquals(mensNode, treeUtil.getNode(1, rootNode));
		assertEquals(womensNode, treeUtil.getNode(6, rootNode));
		assertEquals(shirtsNode, treeUtil.getNode(2, rootNode));
		assertEquals(trousersNode, treeUtil.getNode(3, rootNode));
		assertEquals(casualsNode, treeUtil.getNode(4, rootNode));
	}

}

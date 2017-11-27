package com.sg.calculator.util;

import java.util.List;

public class TreeUtil {

	private Node rootNode;

	public TreeUtil(Node rootNode) {
		super();
		if (null == rootNode)
			throw new RuntimeException("Root Node can't be null");
		this.rootNode = rootNode;
	}

	public TreeUtil() {
		super();
		rootNode = new Node(0, "Root", null, 0);
	}

	public Node getRootNode() {
		return rootNode;
	}

	public Node createNode(List<String> nodeElements) {
		if (null == nodeElements || nodeElements.size() != 4)
			throw new RuntimeException("Node should have all the parameters defined");
		return new Node(Integer.parseInt(nodeElements.get(0).trim()), nodeElements.get(1).trim(), getNode(Integer.parseInt(nodeElements.get(2).trim())),
				Integer.parseInt(nodeElements.get(3).trim()));
	}

	private Node getNode(int nodeId) {
		return getNode(nodeId, rootNode);
	}

	public static Node getNode(int nodeId, Node node) {
		// System.out.println("TreeUtil | getNode | nodeId - " + nodeId + " | node - " +
		// node);
		if (null == node)
			throw new RuntimeException("Node not found");
		if (node.getId() == nodeId)
			return node;

		if (node.hasChildren())
			for (Node child : node.getChildren()) {
				if (child.getId() == nodeId)
					return child;
				Node result = getNode(nodeId, child);
				if (null != result)
					return result;

			}

		return null;
	}

	public static Node getNode(String nodeName, Node node) {
		// System.out.println("TreeUtil | getNode | nodeId - " + nodeName + " | node - "
		// + node);
		if (null == node)
			throw new RuntimeException("Node not found");
		if (node.getName().equals(nodeName))
			return node;

		if (node.hasChildren())
			for (Node child : node.getChildren()) {
				if (child.getName().equals(nodeName))
					return child;
				Node result = getNode(nodeName, child);
				if (null != result)
					return result;

			}

		return null;
	}

}

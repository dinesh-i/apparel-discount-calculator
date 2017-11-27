package com.sg.calculator.util;

import java.util.HashSet;
import java.util.Set;

public class Node {

	private int id;
	private String name;
	private Node parent;
	private int discountPercentage;
	private Set<Node> children = new HashSet<>();

	public Node(int id, String name, Node parent, int discountPercentage) {
		super();
		this.id = id;
		this.name = name;
		this.parent = parent;
		this.discountPercentage = discountPercentage;

		if (null != parent)
			parent.addChild(this);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Node getParent() {
		return parent;
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public Set<Node> getChildren() {
		return children;
	}

	public void addChild(Node child) {
		children.add(child);
	}

	public boolean hasChildren() {
		return children.size() > 0;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", name=" + name + ", parent=" + parent + ", discountPercentage=" + discountPercentage + "]";
	}

}

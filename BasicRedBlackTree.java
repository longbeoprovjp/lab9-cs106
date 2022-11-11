package redblacktree;

import static sbcc.Core.*;

/**
 * CS 106 Theory and Practice II CRN: 38050 Assignment: Lab 9
 * 
 * @author Long Duong
 */
public class BasicRedBlackTree implements RedBlackTree {
	RBTNode root;

	@Override
	public boolean RBTreeSetChild(RBTNode parent, RBNodeType whichChild, RBTNode child) {

		if (whichChild != RBNodeType.LEFT && whichChild != RBNodeType.RIGHT)
			return false;

		if (whichChild == RBNodeType.LEFT)
			parent.left = child;
		else
			parent.right = child;
		if (child != null)
			child.parent = parent;
		return true;
	}


	@Override
	public boolean RBTreeReplaceChild(RBTNode parent, RBTNode currentChild, RBTNode newChild) {
		if (parent.left == currentChild)
			return RBTreeSetChild(parent, RBNodeType.LEFT, newChild);
		else if (parent.right == currentChild)
			return RBTreeSetChild(parent, RBNodeType.RIGHT, newChild);
		return false;
	}


	@Override
	public void RBTreeRotateLeft(RBTNode node) {
		RBTNode rightLeftChild = node.right.left;
		if (node.parent != null)
			RBTreeReplaceChild(node.parent, node, node.right);
		else { // node is root
			root = node.right;
			root.parent = null;
		}
		RBTreeSetChild(node.right, RBNodeType.LEFT, node);
		RBTreeSetChild(node, RBNodeType.RIGHT, rightLeftChild);

	}


	@Override
	public void RBTreeRotateRight(RBTNode node) {
		RBTNode leftRightChild = node.right.left;
		if (node.parent != null)
			RBTreeReplaceChild(node.parent, node, node.left);
		else { // node is root
			root = node.left;
			root.parent = null;
		}
		RBTreeSetChild(node.right, RBNodeType.LEFT, node);
		RBTreeSetChild(node, RBNodeType.RIGHT, leftRightChild);
	}


	@Override
	public void BSTInsert(RBTNode node) {
		if (root == null) {
			root = node;
		} else {
			RBTNode currentNode = root;
			while (currentNode != null) {
				if (node.key < currentNode.key) {
					if (currentNode.left == null) {
						currentNode.left = node;
						currentNode = null;
					} else {
						currentNode = currentNode.left;
					}
				} else {
					if (currentNode.right == null) {
						currentNode.right = node;
						currentNode = null;
					} else {
						currentNode = currentNode.right;
					}
				}
			}
		}
	}


	@Override
	public void RBTreeInsert(RBTNode node) {
		BSTInsert(node);
		node.color = RBNodeColor.RED;
		RBTreeBalance(node);

	}


	@Override
	public RBTNode RBTreeGetGrandparent(RBTNode node) {
		if (node.parent == null)
			return null;
		return node.parent.parent;
	}


	@Override
	public RBTNode RBTreeGetUncle(RBTNode node) {
		RBTNode grandparent = null;
		if (node.parent != null)
			grandparent = node.parent.parent;
		if (grandparent == null)
			return null;
		if (grandparent.left == node.parent)
			return grandparent.right;
		else
			return grandparent.left;
	}


	@Override
	public void RBTreeBalance(RBTNode node) {
		RBTNode parent = node.parent;
		RBTNode uncle = RBTreeGetUncle(node);
		RBTNode grandparent = RBTreeGetGrandparent(node);
		if (root == null) {
			root = node;
			root.color = RBNodeColor.BLACK;
			return;
		}
		if (parent.color == RBNodeColor.BLACK)
			return;
		if (parent.color == RBNodeColor.RED && uncle.color == RBNodeColor.RED) {
			parent.color = RBNodeColor.BLACK;
			uncle.color = RBNodeColor.RED;
			grandparent.color = RBNodeColor.RED;
			RBTreeBalance(grandparent);
			return;
		}
		if (node == parent.right && parent == grandparent.left) {
			RBTreeRotateLeft(parent);
			node = parent;
			parent = node.parent;
			parent.color = RBNodeColor.BLACK;
			grandparent.color = RBNodeColor.RED;
		}
		if (node == parent.left && parent == grandparent.right) {
			RBTreeRotateRight(parent);
			node = parent;
			parent = node.parent;
			parent.color = RBNodeColor.BLACK;
			grandparent.color = RBNodeColor.RED;
		}
		if (node == parent.left) {
			RBTreeRotateRight(grandparent);
		} else
			RBTreeRotateLeft(grandparent);
	}


	@Override
	public void printInOrderRecursive(RBTNode node) {
		if (node == null)
			return;
		printInOrderRecursive(node.left);
		// Print node
		print("Node: " + node.key + " " + " - Color: " + node.color + "\n");
		printInOrderRecursive(node.right);
	}

}

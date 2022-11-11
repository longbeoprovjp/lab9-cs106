package redblacktree;

import static sbcc.Core.*;
import static java.lang.System.*;
import static org.apache.commons.lang3.StringUtils.*;
import static java.util.Arrays.*;
import static java.lang.Math.*;

/**
 * CS 106 Theory and Practice II CRN: 38050 Assignment: Lab 9
 * 
 * @author Long Duong
 */
public class Main {

	public static void main(String[] args) {
		BasicRedBlackTree tree = new BasicRedBlackTree();
		tree.root = new RBTNode(61, RBNodeColor.BLACK);
		tree.root.left = new RBTNode(40, RBNodeColor.BLACK);
		tree.root.right = new RBTNode(80, RBNodeColor.BLACK);
		tree.root.left.left = new RBTNode(16, RBNodeColor.RED);
		tree.root.left.right = new RBTNode(43, RBNodeColor.RED);
		tree.root.right.left = new RBTNode(77, RBNodeColor.RED);
		tree.root.right.right = new RBTNode(92, RBNodeColor.RED);
		println("Red-Black Tree Print in-order");
		tree.printInOrderRecursive(tree.root);
	}

}

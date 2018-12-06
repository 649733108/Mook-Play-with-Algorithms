
/*
 * Created by wxn
 * 2018/12/6 18:01
 */

import util.SortTestHelper;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 二分搜索树
 */
public class BST<Key extends Comparable<Key>, Value> {

	private class Node {
		Key key;
		Value value;
		Node left;
		Node right;

		public Node(Key key, Value value) {
			this.key = key;
			this.value = value;
			left = right = null;
		}

		public Node(Node node) {
			this.key = node.key;
			this.value = node.value;
			this.left = node.left;
			this.right = node.right;
		}
	}

	private Node root;
	private int count;

	public BST() {
		this.root = null;
		this.count = 0;
	}

	// 返回二分搜索树的节点个数
	public int size() {
		return count;
	}

	// 返回二分搜索树是否为空
	public boolean isEmpty() {
		return count == 0;
	}

	public void insert(Key key, Value value) {
		root = insert(root, key, value);
	}

	//向以root为根的BST插入 返回根节点
	private Node insert(Node node, Key key, Value value) {
		if (node == null) {
			count++;
			return new Node(key, value);
		}
		if (key.compareTo(node.key) == 0)
			node.value = value;
		else if (key.compareTo(node.key) < 0)
			node.left = insert(node.left, key, value);
		else
			node.right = insert(node.right, key, value);
		return node;
	}

	//插入--非递归
	public void insertNR(Key key, Value value) {
		if (root == null)
			root = new Node(key, value);
		else {
			Node node = root;
			while (true) {
				if (key.compareTo(node.key) == 0) {
					node.value = value;
					return;
				} else if (key.compareTo(node.key) > 0) {
					if (node.right != null)
						node = node.right;
					else {
						node.right = new Node(key, value);
						return;
					}

				} else {
					if (node.left != null)
						node = node.left;
					else {
						node.left = new Node(key, value);
						return;
					}
				}
			}
		}
	}

	public boolean contain(Key key) {
		return contain(root, key);
	}

	private boolean contain(Node node, Key key) {
		if (node == null)
			return false;
		if (key.compareTo(node.key) == 0)
			return true;
		else if (key.compareTo(node.key) < 0)
			return contain(node.left, key);
		else
			return contain(node.right, key);
	}

	// 在二分搜索树中搜索键key所对应的值。如果这个值不存在, 则返回null
	public Value search(Key key) {
		return search(root, key);
	}

	private Value search(Node node, Key key) {
		if (node == null)
			return null;
		if (key.compareTo(node.key) == 0) {
			return node.value;
		} else if (key.compareTo(node.key) > 0) {
			return search(node.right, key);
		} else {
			return search(node.left, key);
		}
	}

	/**
	 * 寻找最小键值
	 */
	public Key minimum(){
		Node minNode = minimum(root);
		return minNode.key;
	}

	private Node minimum(Node node) {
		if (node.left==null)
			return node;
		return minimum(node.left);
	}

	/**
	 * 寻找最大键值
	 */
	public Key maximum(){
		Node maxNode = maximum(root);
		return maxNode.key;
	}

	private Node maximum(Node node) {
		if (node.right==null)
			return node;
		return maximum(node.right);
	}

	/**
	 * 删除最小节点
	 */
	public void removeMin(){
		if (root!=null){
			 root = removeMin(root);
		}
	}

	private Node removeMin(Node node) {
		if (node.left==null){
			Node rightNode = node.right;
			node.right = null;
			count--;
			return rightNode;
		}
		node.left = removeMin(node.left);
		return node;
	}

	/**
	 * 删除最大节点
	 */
	public void removeMax(){
		if (root!=null){
			root = removeMax(root);
		}
	}

	private Node removeMax(Node node) {
		if (node.right==null){
			Node leftNode = node.left;
			node.left = null;
			count--;
			return leftNode;
		}
		node.right = removeMax(node.right);
		return node;
	}

	/**
	 * 删除任意节点
	 */
	public void remove(Key key){
		if (root!=null){
			root = remove(root,key);
		}
	}
	private Node remove(Node node,Key key){
		if (node==null){
			return null;
		}
		if (key.compareTo(node.key)<0){
			node.left = remove(node.left,key);
			return node;
		}else if (key.compareTo(node.key)>0){
			node.right = remove(node.right,key);
			return node;
		}else {//key==node.key
			if (node.left==null){
				Node rightNode = node.right;
				node.right = null;
				count--;
				return rightNode;
			}
			if (node.right==null){
				Node leftNode = node.left;
				node.left = null;
				count--;
				return leftNode;
			}

			Node successor = new Node(minimum(node.right));
			successor.right  = removeMin(node.right);
			successor.left = node.left;

			node.left = node.right = null;
			count--;

			return successor;
		}
	}

	/**
	 * 先序遍历
	 */
	public void preOrder() {
		preOrder(root);
	}

	private void preOrder(Node node) {
		if (node == null)
			return;
		System.out.print(node.key + " ");
		preOrder(node.left);
		preOrder(node.right);

	}

	/**
	 * 中序遍历
	 */
	public void inOrder() {
		inOrder(root);
	}

	private void inOrder(Node node) {
		if (node == null)
			return;
		inOrder(node.left);
		System.out.print(node.key + " ");
		inOrder(node.right);
	}

	/**
	 * 后序遍历
	 */
	public void postOrder() {
		postOrder(root);
	}

	private void postOrder(Node node) {
		if (node == null)
			return;
		postOrder(node.left);
		postOrder(node.right);
		System.out.print(node.key + " ");
	}

	/**
	 * 层序遍历
	 */
	public void levelOrder() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node node = queue.remove();
			System.out.print(node.key + " ");
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public static void main(String args[]) {
//		Integer[] arr = SortTestHelper.generateRangeInt(10, 1, 100);
		Integer[] arr = {25,34,73,71,57,44,94,27,76,54};
		SortTestHelper.printArr(arr);

		BST<Integer, Integer> bst = new BST<>();
		for (int i = 0; i < arr.length; i++) {
			bst.insertNR(arr[i], arr[i]);
		}
		bst.preOrder();
		System.out.println();
		System.out.println(bst.contain(10));

		bst.inOrder();
		System.out.println();
		bst.postOrder();
		System.out.println();

		bst.levelOrder();
		System.out.println();

		System.out.println("==============");
//		System.out.println("inOrder");
//		bst.inOrder();
//		System.out.println();
//		bst.removeMin();
//		bst.inOrder();
//		System.out.println();
//		bst.removeMax();
//		bst.inOrder();

		System.out.println("inOrder");
		bst.inOrder();
		System.out.println();
		bst.remove(71);
		bst.inOrder();
	}


}

package lab_11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class BST<E extends Comparable<E>> {
	private BNode<E> root;

	public BST() {
		this.root = null;
	}

	// Add element e into BST
	public void add(E e) {
		root = addRecursive(root, e);
	}

	private BNode<E> addRecursive(BNode<E> current, E e) {
		// TODO Auto-generated method stub
		if (current == null) {
			return new BNode<>(e);
		}

		int compareResult = e.compareTo(current.getData());

		if (compareResult < 0) {
			current.setLeft(addRecursive(current.getLeft(), e));
		} else if (compareResult > 0) {
			current.setRight(addRecursive(current.getRight(), e));
		}
		return current;
	}

	// Add a collection of elements col into BST
	public void add(Collection<E> col) {
		// TODO
		for (E element : col) {
			add(element);
		}
	}

	// compute the depth of a node in BST
	public int depth(E node) {
		// TODO
		return depth(root, node, 0);
	}

	private int depth(BNode<E> current, E node, int currentDepth) {
		// TODO Auto-generated method stub
		if (current == null)
			return -1;
		// So sánh giá trị của nút hiện tại với giá trị của nút cần tìm độ sâu
		int compareResult = node.compareTo(current.getData());

		if (compareResult == 0) {
			// Nếu nút hiện tại có giá trị bằng nút cần tìm độ sâu, trả về độ sâu hiện tại
			return currentDepth;
		} else if (compareResult < 0) {
			// Nếu giá trị của nút cần tìm độ sâu nhỏ hơn, di chuyển xuống nút con trái
			return depth(current.getLeft(), node, currentDepth + 1);
		} else {
			// Nếu giá trị của nút cần tìm độ sâu lớn hơn, di chuyển xuống nút con phải
			return depth(current.getRight(), node, currentDepth + 1);
		}
	}

	// compute the height of BST
	public int height() {
		// TODO
		return heightRecursion(root);
	}

	private int heightRecursion(BNode<E> node) {
		// TODO Auto-generated method
		if (node == null)
			return -1;
		int leftHight = heightRecursion(node.getLeft());
		int rightHight = heightRecursion(node.getRight());
		return Math.max(leftHight, rightHight) + 1;
	}

	// Compute total nodes in BST
	public int size() {
		// TODO
		return sizeRecursive(root);
	}

	private int sizeRecursive(BNode<E> node) {
		// TODO Auto-generated method stub
		if (node == null)
			return 0;
		int leftSize = sizeRecursive(node.getLeft());
		int rightSize = sizeRecursive(node.getRight());
		return 1 + leftSize + rightSize;
	}

	// Check whether element e is in BST
	public boolean contains(E e) {
		// TODO
		return contains(root, e);

	}

	private boolean contains(BNode<E> current, E e) {
		// TODO Auto-generated method stub
		if (current == null) {
			return false;
		}
		int compareResult = e.compareTo(current.getData());

		if (compareResult == 0) {
			return true;
		} else if (compareResult < 0) {
			return contains(current.getLeft(), e);
		} else {
			return contains(current.getRight(), e);
		}
	}

	// Find the minimum element in BST
	public E findMin() {
		// TODO
		if (root == null)
			return null;

		BNode<E> current = root;
		while (current.getLeft() != null) {
			current = current.getLeft();
		}
		return current.getData();
	}

	// Find the maximum element in BST
	public E findMax() {
		// TODO
		if (root == null)
			return null;

		BNode<E> current = root;
		while (current.getRight() != null) {
			current = current.getRight();
		}
		return current.getData();

	}

	// Remove element e from BST
	public boolean remove(E e) {
		// TODO
		if (contains(e)) {
			root = remove(root, e);
			return true;
		}
		return false;
	}

	private BNode<E> remove(BNode<E> current, E e) {
		// TODO Auto-generated method stub
		if (current == null) {
			return null;
		}

		int compareResult = e.compareTo(current.getData());

		if (compareResult < 0) {
			current.setLeft(remove(current.getLeft(), e));
		} else if (compareResult > 0) {
			current.setRight(remove(current.getRight(), e));
		} else {

			if (current.getLeft() == null) {
				return current.getRight();
			} else if (current.getRight() == null) {
				return current.getLeft();
			}

			// Case 3: Node with two children
			// Replace the value of the current node with the smallest value
			// in the right subtree (or largest value in the left subtree)
			current.setData(findMin(current.getRight()));

			// Remove the node with the smallest value in the right subtree
			current.setRight(remove(current.getRight(), current.getData()));
		}
		return current;
	}

	private E findMin(BNode<E> node) {
		// TODO Auto-generated method stub
		while (node.getLeft() != null) {
			node = node.getLeft();

		}
		return node.getData();
	}

	// get the descendants of a node
	public List<E> descendants(E data) {
		// TODO
		List<E> descendantsList = new ArrayList<>();
		BNode<E> node = findNode(root,data);

		if (node != null) {
			collectDescendants(node,descendantsList);
		}
		return descendantsList;
	}

	private BNode<E> findNode(BNode<E> current, E data) {
		// TODO Auto-generated method stub
		if (current == null) {
			return null;
		}
		int compareResult = data.compareTo(current.getData());
		if (compareResult ==0) {
			return current;
		} else if (compareResult<0) {
			return findNode(current.getLeft(), data);
		} else {
			return findNode(current.getRight(), data);
		}

	}
	private void collectDescendants(BNode<E> node, List<E> descendantsList) {
		// TODO Auto-generated method stub
		if(node != null) {
			descendantsList.add(node.getData());

		}
		collectDescendants(node.getLeft(), descendantsList);
		collectDescendants(node.getRight(), descendantsList);
	}

	// get the ancestors of a node
	public List<E> ancestors(E data) {
		// TODO

		return null;
	}

	// display BST using inorder approach
	public void inorder() {
		// TODO
	}

	// display BST using preorder approach
	public void preorder() {
		// TODO
	}

	// display BST using postorder approach
	public void postorder() {
		// TODO
	}

	public static void main(String[] args) {
		BST<Integer> t1 = new BST<>();

		t1.add(Arrays.asList(50, 30, 70, 20, 40, 60, 80));

		// In ra độ sâu của một số nút trong cây
		System.out.println("Depth of 50: " + t1.depth(50)); // Kết quả: 0
		System.out.println("Depth of 30: " + t1.depth(30)); // Kết quả: 1
		System.out.println("Depth of 80: " + t1.depth(80)); // Kết quả: 1
		System.out.println("Depth of 20: " + t1.depth(20)); // Kết quả: 2
		System.out.println("Depth of 60: " + t1.depth(60)); // Kết quả: 2
		System.out.println("Depth of 90: " + t1.depth(90)); // Kết quả: -1 (nút không tồn tại)

		System.out.println(t1.height());
		System.out.println(t1.size());

		System.out.println(t1.contains(50));
		System.out.println(t1.findMin());
		System.out.println(t1.findMax());

		 BST<Integer> bst = new BST<>();

		    // Thêm các phần tử vào cây
		    bst.add(Arrays.asList(50, 30, 70, 20, 40, 60, 80));

		    // In ra các con cháu của nút có giá trị 30
		    List<Integer> descendantsOf30 = bst.descendants(30);
		    System.out.println("Descendants of 30: " + descendantsOf30);

		    // In ra các con cháu của nút có giá trị 70
		    List<Integer> descendantsOf70 = bst.descendants(70);
		    System.out.println("Descendants of 70: " + descendantsOf70);

		    // In ra các con cháu của nút có giá trị 90 (nút không tồn tại)
		    List<Integer> descendantsOf90 = bst.descendants(90);
		    System.out.println("Descendants of 90: " + descendantsOf90);
	}
}

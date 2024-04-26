package project20280.tree;

import project20280.interfaces.Position;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Concrete implementation of a binary tree using a node-based, linked
 * structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    static java.util.Random rnd = new java.util.Random();

    private Comparator<E> comparator; // Comparator to use for ordering

    public LinkedBinaryTree(Comparator<E> comp) {
        this.comparator = comp;
    }
    /**
     * The root of the binary tree
     */
    protected Node<E> root = null; // root of the tree

    // LinkedBinaryTree instance variables
    /**
     * The number of nodes in the binary tree
     */
    private int size = 0; // number of nodes in the tree

    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree() {
    } // constructs an empty binary tree

    // constructor

    public static LinkedBinaryTree<Integer> makeRandom(int n) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.root = randomTree(null, 1, n);
        return bt;
    }

    // nonpublic utility

    public static <T extends Integer> Node<T> randomTree(Node<T> parent, Integer first, Integer last) {
        if (first > last) return null;
        else {
            Integer treeSize = last - first + 1;
            Integer leftCount = rnd.nextInt(treeSize);
            Integer rightCount = treeSize - leftCount - 1;
            Node<T> root = new Node<T>((T) ((Integer) (first + leftCount)), parent, null, null);
            root.setLeft(randomTree(root, first, first + leftCount - 1));
            root.setRight(randomTree(root, first + leftCount + 1, last));
            return root;
        }
    }

    // accessor methods (not already implemented in AbstractBinaryTree)

    public static void main(String[] args) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        // Direct construction of Tree
        Position<Integer> root = bt.addRoot(12);
        Position<Integer> p1 = bt.addLeft(root, 25);
        Position<Integer> p2 = bt.addRight(root, 31);

        Position<Integer> p3 = bt.addLeft(p1, 58);
        bt.addRight(p1, 36);

        Position<Integer> p5 = bt.addLeft(p2, 42);
        bt.addRight(p2, 90);

        Position<Integer> p4 = bt.addLeft(p3, 62);
        bt.addRight(p3, 75);

        System.out.println("bt inorder: " + bt.size() + " " + bt.inorder());
        System.out.println("bt preorder: " + bt.size() + " " + bt.preorder());
        System.out.println("bt preorder: " + bt.size() + " " + bt.postorder());

        System.out.println(bt.toBinaryTreeString());
    }

    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    /**
     * Verifies that a Position belongs to the appropriate class, and is not one
     * that has been previously removed. Note that our current implementation does
     * not actually verify that the position belongs to this particular list
     * instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    // update methods supported by this class

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getRight();
    }

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        // TODO
        if (!isEmpty( )) throw new IllegalStateException("Tree is not empty");
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }

    public void insert(E e) {
        // TODO
        if (root == null){
            root = createNode(e, null, null, null);
        }
        Node<E> parent = null;
        Node<E> current = root;

        //int compare = e.compareTo(current.getElement());
        while(current!= null){
            parent = current;
            if((Integer) e < (Integer) current.getElement() ){
                current = current.left;
            }else if (((Integer)e > (Integer) current.getElement())){
                current = current.right;
            }else{




                return;
            }
        }

        if (((Integer)e < (Integer) parent.getElement())) {
            parent.left = new Node<>(e,parent,null,null);
        } else {
            parent.right = new Node<>(e,parent,null,null);
        }
        size++;
    }

    // recursively add Nodes to binary tree in proper position
    private Node<E> addRecursive(Node<E> p, E e) {
        // TODO
        return null;
    }

    /**
     * Creates a new left child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        // TODO
        Node<E> parentNode = validate(p);
        if (parentNode.getLeft() != null) {
            throw new IllegalArgumentException("Node already has a left child");
        }
        Node<E> leftNode = createNode(e,parentNode,null,null);
        parentNode.setLeft(leftNode);
        size++;
        return leftNode;
    }

    /**
     * Creates a new right child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        // TODO
        Node<E> parentNode = validate(p);
        if (parentNode.getRight() != null) {
            throw new IllegalArgumentException("Node already has a Right child");
        }
        Node<E> rightNode =  createNode(e,parentNode,null,null);
        parentNode.setRight(rightNode);
        size++;
        return rightNode;
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced
     * element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        // TODO
        Node<E> node = validate(p); // Validate p and cast to Node<E>.
        E re = p.getElement();
        node.setElement(e);
        return re;
    }
    public void construct(Integer[] inorder, Integer[] preorder) {
        int[] preIndex = new int[1]; // Use an array to hold preIndex so it can be updated
        root =  constructBinaryTree(0, inorder.length-1, preIndex, (E[]) inorder, (E[]) preorder);

    }
    public Node<E> constructBinaryTree(int startIndex, int endIndex, int[] preIndexx, E[] inOrder, E[] preOrder) {
        if (startIndex > endIndex || preIndexx[0] > inOrder.length-1) {// base case
            return null;
        }
        int rootIndex = findIndex(inOrder, preOrder[preIndexx[0]++]);
        Node<E> root = new Node<E>(inOrder[rootIndex],null,null,null);
        root.left = constructBinaryTree(startIndex, rootIndex-1, preIndexx, inOrder,preOrder);
        root.right = constructBinaryTree(rootIndex+1, endIndex, preIndexx,inOrder,preOrder);
        return root;
    }

    private int findIndex(E[] array, E value) {
        int i = 0;
        for (E ee: array) {
            if (array[i].equals(value)) {
                return i;
            }else{
                i++;
            }
        }
        return -1; // Should never happen if input arrays are correct
    }


    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p
     * @param t2 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        // TODO
        Node<E> node = validate(p); // Validate p and cast to Node<E>.

        if (node.getLeft() != null || node.getRight() != null) {
            throw new IllegalArgumentException("Position is not a leaf");
        }

        if (t1 != null && t1.root != null) { // Check if t1 is not empty.
            node.setLeft(t1.root);
            t1.root.setParent(node);
            t1.root = null; // Set t1 to an empty tree.
            t1.size = 0;    // Reset the size of t1.
        }

        if (t2 != null && t2.root != null) { // Check if t2 is not empty.
            node.setRight(t2.root);
            t2.root.setParent(node);
            t2.root = null; // Set t2 to an empty tree.
            t2.size = 0;    // Reset the size of t2.
        }

        size += t1.size() + t2.size();
    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */

    public E remove(Position<E> p) throws IllegalArgumentException {
        // TODO
        Node<E> nodeToRemove = validate(p);
        if (nodeToRemove.getRight() != null && nodeToRemove.getLeft() != null) {
            throw new IllegalArgumentException("Node has 2 children!");
        }

        // Determine the single child, if any
        Node<E> child = (nodeToRemove.getLeft() != null) ? nodeToRemove.getLeft() : nodeToRemove.getRight();

        if (nodeToRemove == root) { // Special case: Node is root
            root = child;
            if (child != null) {
                child.setParent(null);
            }
        } else {
            Node<E> parent = nodeToRemove.getParent();
            // Link the parent directly to the child
            if (parent.getLeft() == nodeToRemove) {
                parent.setLeft(child);
            } else if (parent.getRight() == nodeToRemove) {
                parent.setRight(child);
            }
            if (child != null) {
                child.setParent(parent);
            }
        }

        E elementRemoved = nodeToRemove.getElement();
        nodeToRemove.setElement(null);
        nodeToRemove.setLeft(null);
        nodeToRemove.setRight(null);
        nodeToRemove.setParent(null);
        size--;

        return elementRemoved;
    }
    public String  encodeTree(LinkedBinaryTree<E> tree, E a) {
        if (tree == null) return null;

        StringBuilder path = new StringBuilder();
        boolean found = findPath(tree.root, a, path);

        if (found) {
            //System.out.println("Path to " + a.toString() + ": " + path.toString());
        } else {
            System.out.println(a.toString() + " not found in the tree.");
        }
        return path.toString();
    }

    public boolean findPath(Node<E> node, E element, StringBuilder path) {
        if (node == null) {
            return false; // Base case: not found
        }

        if (element.equals(node.getElement())) {
            return true; // Found the target
        }

        if (findPath(node.getLeft(), element, path)) {
            path.insert(0, "0"); // Prepend "0" for left move
            return true;
        }

        // Search right subtree
        if (findPath(node.getRight(), element, path)) {
            path.insert(0, "1"); // Prepend "1" for right move
            return true;
        }

        // Not found in either subtree, backtrack
        return false;
    }


    public String toString() {
        return positions().toString();
    }

    public void createLevelOrder(ArrayList<E> list) {
        root = createLevelOrderHelper(list, root, 0);
    }


    private Node<E> createLevelOrderHelper(java.util.ArrayList<E> list, Node<E> p, int i) {
        Queue<Node<E>> queue = new LinkedList<>();
        this.root = createNode(list.get(0), null, null, null);
        queue.offer(this.root);
        this.size = 1;

        final int n = list.size();
        i = 1;
        while (i < n) {
            Node<E> parent = queue.poll();

            if (i < n && list.get(i) != null) {
                Node<E> leftChild = createNode(list.get(i), parent, null, null);
                parent.left = leftChild;
                queue.offer(leftChild);
                this.size++;
            }
            i++;

            // Create and add the right child.
            if (i < n && list.get(i) != null) {
                Node<E> rightChild = createNode(list.get(i), parent, null, null);
                parent.right = rightChild;
                queue.offer(rightChild);
                this.size++;
            }
            i++;
        }

        return root;
    }

    public void createLevelOrder(E[] arr) {
        root = createLevelOrderHelper(arr, root, 0);
    }

    private Node<E> createLevelOrderHelper(E[] arr, Node<E> p, int i) {
        // TODO
        if (i < arr.length) {
            Node<E> n = createNode(arr[i], p, null, null);
            n.left = createLevelOrderHelper(arr, n.left, 2 * i + 1);
            n.right = createLevelOrderHelper(arr, n.right, 2 * i + 2);
            ++size;
            return n;
        }
        return p;
    }

    private Node<E> insertInOrder(Node<E> node, E e) {
        if (node == null) {
            return new Node<>(e, null, null, null);
        }
        if (comparator.compare(e, node.getElement()) < 0) {
            node.setLeft(insertInOrder(node.getLeft(), e));
        } else {
            node.setRight(insertInOrder(node.getRight(), e));
        }
        return node;
    }


    public String toBinaryTreeString() {
        BinaryTreePrinter<E> btp = new BinaryTreePrinter<>(this);
        return btp.print();
    }
    public void printLeafNodes() {
        printLeafNodes(root);
    }

    private void printLeafNodes(Node<E> node) {
        if (node != null) {
            if (node.left == null && node.right == null) {  // Check if it is a leaf node
                System.out.print(node.element + " ");
            } else {
                printLeafNodes(node.left);  // Recur on the left subtree
                printLeafNodes(node.right); // Recur on the right subtree
            }
        }
    }

    public Node<E> getNode(Position<E> p) {
        if (p instanceof Node) {
            return (Node<E>) p;
        } else {
            throw new IllegalArgumentException("Invalid position type");
        }
    }

    /**
     * Nested static class for a binary tree node.
     */
    public static class Node<E> implements Position<E> {
        private E element;
        private Node<E> left, right, parent;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            element = e;
            left = l;
            right = r;
            parent = p;
        }

        // accessor
        public E getElement() {
            return element;
        }

        // modifiers
        public void setElement(E e) {
            element = e;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> n) {
            left = n;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> n) {
            right = n;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> n) {
            parent = n;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (element == null) {
                sb.append("\u29B0");
            } else {
                sb.append(element);
            }
            return sb.toString();
        }
    }
}

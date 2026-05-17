package structures;

import model.Product;

public class ProductTree {
    private ProductNode root;

    public ProductTree() {
        this.root = null;
    }

    // --- MUST: Algorithm 1 - Search Lookup (Exact match to Lecture 05, Slide 37) ---
    public ProductNode lookup(int key) {
        return lookup(this.root, key);
    }

    private ProductNode lookup(ProductNode n, int key) {
        if (n == null) {
            return null;
        }
        if (key == n.getKey()) {
            return n;
        }
        if (key < n.getKey()) {
            return lookup(n.getLeft(), key);
        } else {
            return lookup(n.getRight(), key);
        }
    }

    // --- MUST: Algorithm 2 - Inorder Traversal (Sorted Inventory Output) ---
    public void printInOrder() {
        printInOrder(this.root);
    }

    private void printInOrder(ProductNode n) {
        if (n != null) {
            printInOrder(n.getLeft());                // 1. Gehe in den linken Teilbaum (kleinere IDs)
            System.out.println(n.getProduct());       // 2. Verarbeite das aktuelle Produkt
            printInOrder(n.getRight());               // 3. Gehe in den rechten Teilbaum (größere IDs)
        }
    }

    // --- Recursive BST Insertion ---
    public void insert(Product product) {
        if (product == null) return;
        this.root = insert(this.root, product);
    }

    private ProductNode insert(ProductNode current, Product product) {
        if (current == null) {
            return new ProductNode(product);
        }

        if (product.getId() < current.getKey()) {
            current.setLeft(insert(current.getLeft(), product));
        } else if (product.getId() > current.getKey()) {
            current.setRight(insert(current.getRight(), product));
        } else {
            System.out.println("[Warning] Product ID " + product.getId() + " already exists!");
        }
        return current;
    }

    public ProductNode getRoot() {
        return root;
    }
}
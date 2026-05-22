package structures;

import model.Product;
/**
 * Binary Search Tree for storing products by their unique product ID.
 *
 * Used algorithms / data structures:
 * - Binary Search Tree insertion
 * - Recursive lookup
 * - In order traversal
 * - Quicksort for sorting products by price
 */
public class ProductTree {
    private ProductNode root;

    public ProductTree() {
        this.root = null;
    }

    // --- MEILENSTEIN 2: BST Lookup ---

    /**
     * Searches for a product node by product ID.
     *
     * BST rule:
     * - smaller IDs are searched in the left subtree
     * - larger IDs are searched in the right subtree
     *
     * @param targetId product ID to search for
     * @return matching ProductNode or null if not found
     */
    public ProductNode lookup(int targetId) {
        return lookup(this.root, targetId);
    }

    private ProductNode lookup(ProductNode node, int targetId) {
        if (node == null) return null;
        if (targetId == node.getId()) return node;

        if (targetId < node.getId()) {
            return lookup(node.getLeft(), targetId);
        } else {
            return lookup(node.getRight(), targetId);
        }
    }

    // --- MEILENSTEIN 2: Inorder Traversal ---


    /**
     * Prints all products in ascending order by product ID.
     *
     * Inorder traversal visits:
     * left subtree -> current node -> right subtree.
     */
    public void printInOrder() {
        printInOrder(this.root);
    }

    private void printInOrder(ProductNode n) {
        if (n != null) {
            printInOrder(n.getLeft());
            System.out.println(n.getProduct());
            printInOrder(n.getRight());
        }
    }


    /**
     * Inserts a product into the BST.
     *
     * Before insertion:
     * - invalid products are rejected
     * - duplicate product IDs are rejected
     *
     * This protects the tree from invalid data and duplicate keys.
     *
     * @param product product to insert
     */
    // Hilfsmethode zum Einfügen aus M2
    public void insert(Product product) {
        if (product == null || product.getName().contains("[INVALID]")) {
            System.out.println("[BST REJECT] Insertion skipped because the product attributes are invalid.");
            return;
        }

        if (lookup(product.getId()) != null) {
            System.out.println("[BST WARNING] Product ID " + product.getId() + " already exists. Insertion skipped.");
            return;
        }

        this.root = insertRecursive(this.root, product);
    }


    // =========================================================================
// --- C1: Inventory Transaction Simulation (Sell / Restock Stock) ---
// =========================================================================

    public void sellProduct(int productId, int quantity) {
        ProductNode node = lookup(productId);

        if (node == null) {
            System.out.println("[SELL ERROR] Product ID " + productId + " does not exist.");
            return;
        }

        if (quantity <= 0) {
            System.out.println("[SELL ERROR] Quantity must be greater than 0.");
            return;
        }

        Product product = node.getProduct();

        if (quantity > product.getQuantity()) {
            System.out.println("[SELL ERROR] Not enough stock for product ID " + productId + ".");
            return;
        }

        product.setQuantity(product.getQuantity() - quantity);

        System.out.println("[SELL SUCCESS] Sold " + quantity + " unit(s) of " + product.getName()
                + ". New stock: " + product.getQuantity());

        if (product.getQuantity() == 0) {
            System.out.println("[LOW INVENTORY WARNING] Product ID " + productId + " is out of stock.");
        }
    }

    public void restockProduct(int productId, int quantity) {
        ProductNode node = lookup(productId);

        if (node == null) {
            System.out.println("[RESTOCK ERROR] Product ID " + productId + " does not exist.");
            return;
        }

        if (quantity <= 0) {
            System.out.println("[RESTOCK ERROR] Quantity must be greater than 0.");
            return;
        }

        Product product = node.getProduct();
        product.setQuantity(product.getQuantity() + quantity);

        System.out.println("[RESTOCK SUCCESS] Added " + quantity + " unit(s) to " + product.getName()
                + ". New stock: " + product.getQuantity());
    }

    private ProductNode insertRecursive(ProductNode current, Product product) {
        if (current == null) return new ProductNode(product);
        if (product.getId() < current.getId()) {
            current.setLeft(insertRecursive(current.getLeft(), product));
        } else if (product.getId() > current.getId()) {current.setRight(insertRecursive(current.getRight(), product));
        }
        return current;
    }

    // =========================================================================
    // --- MEILENSTEIN 3: Quicksort nach Preis ---
    // =========================================================================

    // 1. Zählt alle Knoten im Baum für die Array-Größe
    public int getSize() {
        return countNodes(this.root);
    }

    private int countNodes(ProductNode node) {
        if (node == null) return 0;
        return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
    }

    // 2. Wandelt den Baum über Inorder-Traversal in ein Array um
    public Product[] toArray() {
        Product[] array = new Product[getSize()];
        fillArrayInOrder(this.root, array, new int[]{0});
        return array;
    }

    private void fillArrayInOrder(ProductNode node, Product[] array, int[] index) {
        if (node != null) {
            fillArrayInOrder(node.getLeft(), array, index);
            array[index[0]++] = node.getProduct();
            fillArrayInOrder(node.getRight(), array, index);
        }
    }


    /**
     * Sorts the given product array by price using Quicksort.
     *
     * Quicksort recursively partitions the array around a pivot element.
     *
     * @param array product array to sort
     */

    // 3. Der Quicksort-Algorithmus (In-place Partitionierung nach Preis)
    public void quickSortByPrice(Product[] array) {
        if (array == null || array.length == 0) return;
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(Product[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }


    /**
     * Partitions the array around the pivot price.
     *
     * Products with price <= pivot are moved to the left side.
     * Products with price > pivot remain on the right side.
     *
     * @param array product array
     * @param low start index
     * @param high end index, also used as pivot position
     * @return final pivot index
     */
    private int partition(Product[] array, int low, int high) {
        double pivotPrice = array[high].getPrice();
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (array[j].getPrice() <= pivotPrice) {
                i++;
                Product temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        Product temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    public double calculateTotalWarehouseValue() {
        return calculateTotalWarehouseValue(this.root);
    }

    private double calculateTotalWarehouseValue(ProductNode node) {
        if (node == null) return 0;

        Product product = node.getProduct();
        double currentValue = product.getPrice() * product.getQuantity();

        return currentValue
                + calculateTotalWarehouseValue(node.getLeft())
                + calculateTotalWarehouseValue(node.getRight());
    }

}
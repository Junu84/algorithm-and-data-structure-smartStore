import model.Product;
import structures.ProductTree;
import structures.ProductNode;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SmartStore Warehouse Simulation ===");

        // 1. Baum-Instanz erstellen
        ProductTree warehouse = new ProductTree();

        // 2. Testprodukte einfügen
        System.out.println("Inserting products into Binary Search Tree...");
        warehouse.insert(new Product(105, "Gaming Mouse", 49.99, 15));
        warehouse.insert(new Product(101, "Mechanical Keyboard", 89.99, 8));
        warehouse.insert(new Product(112, "WQHD Monitor", 299.00, 4));
        warehouse.insert(new Product(108, "USB-C Hub", 24.50, 22));

        System.out.println("Insertion completed successfully!\n");

        // --- MEILENSTEIN 2 TESTS (Keep them to ensure no regressions) ---
        System.out.println("=== Testing M2: BST Lookup & Inorder ===");
        int searchId = 108;
        ProductNode foundNode = warehouse.lookup(searchId);

        if (foundNode != null) {
            System.out.println("[SUCCESS] Product found in tree: " + foundNode.getProduct());
        } else {
            System.out.println("[ERROR] Product with ID " + searchId + " does not exist.");
        }

        System.out.println("\nPrinting standard BST Inorder (Sorted by ID):");
        warehouse.printInOrder();


        // =========================================================================
        // --- MEILENSTEIN 3 TEST: Sort Products by Price (Quicksort) ---
        // =========================================================================
        System.out.println("\n=== Testing M3: Sort Products by Price (Quicksort) ===");

        // 1. Mirror tree elements into a temporary array
        System.out.println("Mirroring BST nodes into a flat array...");
        Product[] productArray = warehouse.toArray();

        // 2. Run the in-place partitioning Quicksort algorithm
        System.out.println("Executing Quicksort by price...");
        warehouse.quickSortByPrice(productArray);

        // 3. Print the sorted array to verify success
        System.out.println("\n[SUCCESS] Products instantly sorted by price (Low to High):");
        for (Product p : productArray) {
            System.out.println(p);
            // This will automatically invoke the formatted toString() method from Product.java
        }
    }
}
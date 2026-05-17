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

        // 3. Den Such-Algorithmus testen (Exakt nach Folie 37!)
        int searchId = 108;
        System.out.println("Running Lookup Algorithm for Product ID: " + searchId);

        ProductNode foundNode = warehouse.lookup(searchId);

        if (foundNode != null) {
            System.out.println("\n[SUCCESS] Product found in tree:");
            System.out.println(foundNode.getProduct());
        } else {
            System.out.println("\n[ERROR] Product with ID " + searchId + " does not exist in the warehouse.");
        }

        // 4. Das Inorder-Traversal testen (Sortierte Ausgabe des Inventars)
        System.out.println("\n=== Testing Inorder Traversal (Sorted Inventory) ===");
        warehouse.printInOrder();
    }



}
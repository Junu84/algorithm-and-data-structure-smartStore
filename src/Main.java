import model.Product;
import model.Customer;
import structures.ProductTree;
import structures.ProductNode;
import structures.CustomerHashTable;

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

        // --- MEILENSTEIN 2 TESTS ---
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

        // --- MEILENSTEIN 3 TEST: Sort Products by Price (Quicksort) ---
        System.out.println("\n=== Testing M3: Sort Products by Price (Quicksort) ===");
        Product[] productArray = warehouse.toArray();
        warehouse.quickSortByPrice(productArray);

        System.out.println("[SUCCESS] Products instantly sorted by price (Low to High):");
        for (Product p : productArray) {
            System.out.println(p);
        }

        // =========================================================================
        // --- MEILENSTEIN 4 TEST: Customer Profile Management (Hash Table) ---
        // =========================================================================
        System.out.println("\n=== Testing Customer Hash Table (Division Method & Linear Probing) ===");

        // Tabelle mit fixer Größe von 10 Slots initialisieren (m = 10)
        CustomerHashTable customerDb = new CustomerHashTable(10);

        System.out.println("Registering customers...");
        customerDb.put(new Customer(102, "Alice"));
        customerDb.put(new Customer(105, "Bob"));

        // ID 202 provoziert eine Kollision: 202 % 10 = 2. Slot 2 ist besetzt durch Alice (102 % 10 = 2).
        // Lineares Sondieren schiebt Charlie automatisch in Slot 3 vor!
        System.out.println("Registering Charlie (ID 202) -> Should trigger collision resolution...");
        customerDb.put(new Customer(202, "Charlie"));

        // Tabelle ausgeben, um die Indizes visuell zu prüfen
        customerDb.printTable();

        // Abruf trotz Kollision testen
        System.out.println("\nVerifying Retrieval System:");
        int targetCustomerId = 202;
        Customer searched = customerDb.get(targetCustomerId);

        if (searched != null) {
            System.out.println("[SUCCESS] Retrieved " + searched + " correctly bypassing hash conflicts!");
        } else {
            System.out.println("[ERROR] Failed to find Customer ID " + targetCustomerId);
        }
    }
}
import model.Product;
import model.Customer;
import structures.ProductTree;
import structures.ProductNode;
import structures.CustomerHashTable;



public class Main {
    public static void main(String[] args) {
        System.out.println("=== SmartStore Warehouse Simulation - Sprint 1 (Validation) ===");


        System.out.println("=================================");
        System.out.println("      SMART STORE SYSTEM");
        System.out.println("=================================");


        // 1. Baum-Instanz erstellen
        //  Creates the product tree used as the warehouse storage.
        // Products are stored by their product ID using BST rules.
        ProductTree warehouse = new ProductTree();


        // 2. Testprodukte einfügen (Valid Products)
        System.out.println("\n--- Inserting Valid Products ---");
        warehouse.insert(new Product(105, "Gaming Mouse", 49.99, 15));
        warehouse.insert(new Product(101, "Mechanical Keyboard", 89.99, 8));
        warehouse.insert(new Product(112, "WQHD Monitor", 299.00, 4));
        warehouse.insert(new Product(108, "USB-C Hub", 24.50, 22));

        System.out.println("\n=== Testing S2: Duplicate Product ID ===");
        // S2 test: Try to insert a product with an already existing ID.
        // Expected result: insertion is rejected and the original product remains unchanged.
        warehouse.insert(new Product(105, "Duplicate Gaming Mouse", 19.99, 3));


        // =========================================================================
        // --- SPRINT 1 CORE TEST: Rejecting Invalid Attributes ---
        // =========================================================================
        System.out.println("\n=== Testing S1 Requirements: Attribute Validation ===");

        // Test Case A: Invalid Negative Price
        System.out.println("Attempting to insert product with negative price...");
        warehouse.insert(new Product(115, "Broken Price GPU", -399.99, 5));

        // Test Case B: Invalid Negative Stock Quantity
        System.out.println("Attempting to insert product with negative stock...");
        warehouse.insert(new Product(120, "Ghost Headset", 59.99, -10));

        // Test Case C: Both Invalid
        System.out.println("Attempting to insert product with negative price AND stock...");
        warehouse.insert(new Product(125, "Void Item", -10.00, -5));

        System.out.println("Validation checks processed successfully!\n");


        // --- MEILENSTEIN 2 TESTS ---
        System.out.println("=== Testing M2: BST Lookup & Inorder ===");
        int searchId = 108;
        ProductNode foundNode = warehouse.lookup(searchId);

        if (foundNode != null) {
            System.out.println("[SUCCESS] Product found in tree: " + foundNode.getProduct());
        } else {
            System.out.println("[ERROR] Product with ID " + searchId + " does not exist.");
        }

        System.out.println("\nPrinting standard BST Inorder (Valid products only):");
        warehouse.printInOrder();


        // --- MEILENSTEIN 3 TEST: Sort Products by Price (Quicksort) ---
        System.out.println("\n=== Testing M3: Sort Products by Price (Quicksort) ===");
        Product[] productArray = warehouse.toArray();
        warehouse.quickSortByPrice(productArray);

        System.out.println("[SUCCESS] Valid products sorted by price (Low to High):");
        for (Product p : productArray) {
            System.out.println(p);
        }


        // =========================================================================
        // --- MEILENSTEIN 4 TEST: Customer Profile Management (Hash Table) ---
        // =========================================================================
        System.out.println("\n=== Testing Customer Hash Table (Division Method & Linear Probing) ===");

        // Create a customer hash table with capacity 10.
        // Customer IDs are mapped to array positions using the division method.
        CustomerHashTable customerDb = new CustomerHashTable(10);

        System.out.println("Registering customers...");
        customerDb.put(new Customer(102, "Alice"));
        customerDb.put(new Customer(105, "Bob"));

        System.out.println("Registering Charlie (ID 202) -> Should trigger collision resolution...");
        customerDb.put(new Customer(202, "Charlie"));

        System.out.println("\n=== Testing S2: Duplicate Customer ID ===");
        // S2 test: Try to insert a customer with an already existing ID.
        // Expected result: insertion is rejected and the original customer is not overwritten.
        customerDb.put(new Customer(102, "Duplicate Alice"));


        customerDb.printTable();

        System.out.println("\nVerifying Retrieval System:");
        int targetCustomerId = 202;
        Customer searched = customerDb.get(targetCustomerId);

        if (searched != null) {
            System.out.println("[SUCCESS] Retrieved " + searched + " correctly bypassing hash conflicts!");
        } else {
            System.out.println("[ERROR] Failed to find Customer ID " + targetCustomerId);
        }


        System.out.println("\n=== Testing C2: Dynamic Resizing & Rehashing ===");

        CustomerHashTable testTable = new CustomerHashTable(4);

        testTable.put(new Customer(1, "Customer A"));
        testTable.put(new Customer(2, "Customer B"));
        testTable.put(new Customer(3, "Customer C")); // 3/4 = 0.75 -> resize should happen

        testTable.printTable();

        Customer found = testTable.get(2);
        if (found != null) {
            System.out.println("[SUCCESS] Customer still found after rehashing: " + found);
        } else {
            System.out.println("[ERROR] Customer not found after rehashing.");
        }


        System.out.println("\n=== C3: Analytics Dashboard ===");
        System.out.println("Total warehouse value: " + warehouse.calculateTotalWarehouseValue() + " EUR");
        System.out.println("Registered customers: " + customerDb.getCustomerCount());
    }



}
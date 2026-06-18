import model.Product;
import model.Customer;
import structures.ProductTree;
import structures.ProductNode;
import structures.CustomerHashTable;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Beide Datenstrukturen initialisieren
        ProductTree warehouse = new ProductTree();
        CustomerHashTable customerRegistry = new CustomerHashTable(10); // Startkapazität 10
        Scanner scanner = new Scanner(System.in);

        // --- 1. Testdaten vorab laden ---
        warehouse.insert(new Product(105, "Gaming Mouse", 49.99, 15));
        warehouse.insert(new Product(101, "Mechanical Keyboard", 89.99, 8));
        warehouse.insert(new Product(112, "WQHD Monitor", 299.00, 4));
        warehouse.insert(new Product(108, "USB-C Hub", 24.50, 22));

        customerRegistry.put(new Customer(1, "Alice"));
        customerRegistry.put(new Customer(2, "Bob"));
        customerRegistry.put(new Customer(3, "Charlie"));

        boolean running = true;
        while (running) {
            System.out.println("\n=======================================");
            System.out.println("   SMARTSTORE MANAGEMENT ENVIRONMENT   ");
            System.out.println("=======================================");
            System.out.println("1. [Tree] View Sorted Inventory (Inorder)");
            System.out.println("2. [Tree] Search Product by ID (Lookup)");
            System.out.println("3. [Tree] Add New Product");
            System.out.println("---------------------------------------");
            System.out.println("4. [Hash] View All Customers (PrintTable)");
            System.out.println("5. [Hash] Search Customer by ID (Get)");
            System.out.println("6. [Hash] Add New Customer (Put)");
            System.out.println("---------------------------------------");
            System.out.println("7. Exit Application");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Warehouse Inventory (Sorted by ID) ---");
                    warehouse.printInOrder();
                    break;

                case 2:
                    System.out.print("\nEnter Product ID to search: ");
                    int searchProdId = scanner.nextInt();
                    ProductNode foundProd = warehouse.lookup(searchProdId);
                    if (foundProd != null) {
                        System.out.println("\n[SUCCESS] " + foundProd.getProduct());
                    } else {
                        System.out.println("\n[ERROR] Product ID " + searchProdId + " not found.");
                    }
                    break;

                case 3:
                    System.out.print("\nEnter Product ID: ");
                    int prodId = scanner.nextInt();
                    scanner.nextLine(); // Puffer leeren
                    System.out.print("Enter Product Name: ");
                    String prodName = scanner.nextLine();
                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter Stock: ");
                    int stock = scanner.nextInt();

                    warehouse.insert(new Product(prodId, prodName, price, stock));
                    System.out.println("\n[SUCCESS] Product added to warehouse.");
                    break;

                case 4:
                    System.out.println();
                    customerRegistry.printTable();
                    System.out.println("Total Customers: " + customerRegistry.getCustomerCount());
                    break;

                case 5:
                    System.out.print("\nEnter Customer ID to search: ");
                    int searchCustId = scanner.nextInt();
                    Customer foundCust = customerRegistry.get(searchCustId);
                    if (foundCust != null) {
                        System.out.println("\n[SUCCESS] Customer Found: ID=" + foundCust.getId() + ", Name=" + foundCust.getName());
                    } else {
                        System.out.println("\n[ERROR] Customer ID " + searchCustId + " not found in system.");
                    }
                    break;

                case 6:
                    System.out.print("\nEnter Customer ID: ");
                    int custId = scanner.nextInt();
                    scanner.nextLine(); // Puffer leeren
                    System.out.print("Enter Customer Name: ");
                    String custName = scanner.nextLine();

                    customerRegistry.put(new Customer(custId, custName));
                    System.out.println("\n[SUCCESS] Customer registered successfully.");
                    break;

                case 7:
                    running = false;
                    System.out.println("\nShutting down SmartStore simulation. Goodbye!");
                    break;

                default:
                    System.out.println("\n[Invalid Option] Please select a valid menu index.");
            }
        }
        scanner.close();
    }
}
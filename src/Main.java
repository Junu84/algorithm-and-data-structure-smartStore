import model.Product;
import model.Customer;
import structures.ProductTree;
import structures.ProductNode;
import structures.CustomerHashTable;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. Initialize our data structures and sample data
        ProductTree warehouse = new ProductTree();
        CustomerHashTable customerDb = new CustomerHashTable(10);

        // Pre-populating Data for Testing
        warehouse.insert(new Product(105, "Gaming Mouse", 49.99, 15));
        warehouse.insert(new Product(101, "Mechanical Keyboard", 89.99, 8));
        warehouse.insert(new Product(112, "WQHD Monitor", 299.00, 4));
        warehouse.insert(new Product(108, "USB-C Hub", 24.50, 22));

        customerDb.put(new Customer(102, "Alice"));
        customerDb.put(new Customer(105, "Bob"));
        customerDb.put(new Customer(202, "Charlie"));

        // 2. Continuous Loop-Driven Console UI
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Welcome to the SmartStore Warehouse Management System ===");

        while (running) {
            System.out.println("\n------------------------------------------------");
            System.out.println("Please select an option:");
            System.out.println("1) Search for a Product (BST Lookup)");
            System.out.println("2) Display All Products Sorted by Price (Quicksort)");
            System.out.println("3) Search for a Customer Profile (Hash Table Lookup)");
            System.out.println("4) Exit Program");
            System.out.print("Your choice: ");

            int choice = readSafeInteger(scanner);

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID to look up: ");
                    int pId = readSafeInteger(scanner);
                    if (pId != -1) {
                        ProductNode node = warehouse.lookup(pId);
                        if (node != null) {
                            System.out.println("[FOUND] " + node.getProduct());
                        } else {
                            System.out.println("[NOT FOUND] No product with ID " + pId);
                        }
                    }
                    break;

                case 2:
                    System.out.println("\nProcessing Quicksort...");
                    Product[] array = warehouse.toArray();
                    warehouse.quickSortByPrice(array);
                    System.out.println("Products Sorted by Price (Low to High):");
                    for (Product p : array) {
                        System.out.println(" - " + p);
                    }
                    break;

                case 3:
                    System.out.print("Enter Customer ID to look up: ");
                    int cId = readSafeInteger(scanner);
                    if (cId != -1) {
                        Customer customer = customerDb.get(cId);
                        if (customer != null) {
                            System.out.println("[FOUND] " + customer);
                        } else {
                            System.out.println("[NOT FOUND] No customer profile with ID " + cId);
                        }
                    }
                    break;

                case 4:
                    System.out.println("Exiting SmartStore Simulation. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("[WARNING] Invalid menu choice. Please select a number between 1 and 4.");
                    break;
            }
        }
        scanner.close();
    }

    /**
     * Helper Method: Validates scanner inputs against InputMismatchExceptions (Letters instead of Numbers)
     * as explicitly required by the M5 issue description.
     */
    private static int readSafeInteger(Scanner scanner) {
        try {
            int val = scanner.nextInt();
            scanner.nextLine(); // Clear the dangling newline character buffer
            return val;
        } catch (InputMismatchException e) {
            System.out.println("[INPUT ERROR] Invalid input format! You must enter a numeric ID number.");
            scanner.nextLine(); // Completely clear the invalid text entry out of the scanner stream
            return -1; // Return sentinel value signaling invalid input
        }
    }
}
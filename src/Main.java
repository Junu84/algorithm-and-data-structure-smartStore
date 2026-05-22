import model.Product;
import model.Customer;
import structures.CustomerHashTable;
import structures.ProductTree;

public class Main {
    public static void main(String[] args) {

        printWelcomeHeader();

        ProductTree warehouse = new ProductTree();
        CustomerHashTable customerDb = new CustomerHashTable(10);

        loadDemoProducts(warehouse);
        loadDemoCustomers(customerDb);

        demoInventoryTransactions(warehouse);
        demoSortedProducts(warehouse);
        showAnalyticsDashboard(warehouse, customerDb);
    }

    private static void printWelcomeHeader() {
        System.out.println("=================================");
        System.out.println("        SMART STORE SYSTEM");
        System.out.println("=================================");
    }

    private static void loadDemoProducts(ProductTree warehouse) {
        System.out.println("\n--- Loading Products ---");
        warehouse.insert(new Product(105, "Gaming Mouse", 49.99, 15));
        warehouse.insert(new Product(101, "Mechanical Keyboard", 89.99, 8));
        warehouse.insert(new Product(112, "WQHD Monitor", 299.00, 4));
        warehouse.insert(new Product(108, "USB-C Hub", 24.50, 22));
    }

    private static void loadDemoCustomers(CustomerHashTable customerDb) {
        System.out.println("\n--- Registering Customers ---");
        customerDb.put(new Customer(102, "Alice"));
        customerDb.put(new Customer(105, "Bob"));
        customerDb.put(new Customer(202, "Charlie"));
    }

    private static void demoInventoryTransactions(ProductTree warehouse) {
        System.out.println("\n--- Sell / Restock Demo ---");
        warehouse.sellProduct(105, 3);
        warehouse.restockProduct(105, 5);
    }

    private static void demoSortedProducts(ProductTree warehouse) {
        System.out.println("\n--- Products Sorted by Price ---");
        Product[] products = warehouse.toArray();
        warehouse.quickSortByPrice(products);

        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static void showAnalyticsDashboard(ProductTree warehouse, CustomerHashTable customerDb) {
        System.out.println("\n--- Analytics Dashboard ---");
        System.out.println("Total warehouse value: " + warehouse.calculateTotalWarehouseValue() + " EUR");
        System.out.println("Registered customers: " + customerDb.getCustomerCount());
    }
}
package structures;

import model.Customer;
import structures.CustomerHashTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class CustomerHashTableTest {

    private CustomerHashTable hashTable;
    private Customer customer1;
    private Customer customer2;
    private Customer customerColliding;

    @BeforeEach
    void setUp() {
        // Initializing with a small initial capacity of 5 slots to easily test collisions/resizing
        hashTable = new CustomerHashTable(5);

        customer1 = new Customer(1, "Alice");   // hash(1) -> slot 1
        customer2 = new Customer(2, "Bob");     // hash(2) -> slot 2

        // This ID will collide with customer1 when capacity is 5: hash(6) -> 6 % 5 = slot 1
        customerColliding = new Customer(6, "Charlie");
    }

    @Test
    void put() {
        // Test basic tracking
        hashTable.put(customer1);
        assertEquals(1, hashTable.getCustomerCount(), "Count should be 1 after inserting a single customer.");

        // Test duplicate key restriction
        hashTable.put(customer1);
        assertEquals(1, hashTable.getCustomerCount(), "Duplicate key insertion should be rejected and not increase count.");
    }

    @Test
    void get() {
        hashTable.put(customer1);
        hashTable.put(customer2);

        // Verify standard lookups
        Customer retrieved = hashTable.get(1);
        assertNotNull(retrieved);
        assertEquals("Alice", retrieved.getName());

        // Verify invalid lookups
        assertNull(hashTable.get(999), "Looking up a non-existent key should return null.");
    }

    @Test
    void testLinearProbingCollision() {
        // Insert customer1 into Slot 1
        hashTable.put(customer1);

        // Insert customerColliding (ID 6). Since 6 % 5 = 1, it collides with Alice
        // Linear probing should push Charlie into Slot 2 automatically
        hashTable.put(customerColliding);

        assertEquals(2, hashTable.getCustomerCount(), "Both items should be inserted successfully.");

        // Ensure both are perfectly accessible via their keys despite the collision path
        assertEquals("Alice", hashTable.get(1).getName());
        assertEquals("Charlie", hashTable.get(6).getName());
    }

    @Test
    void testResizeAndRehash() {
        // With capacity = 5, load factor threshold is 0.7 -> 5 * 0.7 = 3.5 elements.
        // Inserting the 4th element will cross the threshold and trigger your resize() method.
        hashTable.put(customer1); // count = 1
        hashTable.put(customer2); // count = 2
        hashTable.put(new Customer(3, "David")); // count = 3

        // This 4th insertion triggers resize(): capacity goes 5 -> 10
        hashTable.put(new Customer(4, "Eva"));

        // Let's assert that all previously added items are still completely searchable
        // after being re-hashed into the new doubled capacity array layout
        assertNotNull(hashTable.get(1));
        assertNotNull(hashTable.get(2));
        assertNotNull(hashTable.get(3));
        assertNotNull(hashTable.get(4));
    }

    @Test
    void printTable() {
        hashTable.put(customer1);
        assertDoesNotThrow(() -> hashTable.printTable(), "printTable should run smoothly without any runtime crashes.");
    }

    @Test
    void getCustomerCount() {
        assertEquals(0, hashTable.getCustomerCount());
        hashTable.put(customer1);
        assertEquals(1, hashTable.getCustomerCount());
    }
}
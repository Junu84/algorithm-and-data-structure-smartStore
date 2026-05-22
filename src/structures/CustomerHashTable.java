package structures;

import model.Customer;


/**
 * Hash table for storing customers by their unique customer ID.
 *
 * Used algorithms / data structures:
 * - Hashing with division method: h(k) = k mod m
 * - Linear probing for collision resolution
 */

public class CustomerHashTable {
    private Customer[] table;
    private int size; // This is 'm' from your lecture
    private int count;

    public CustomerHashTable(int capacity) {
        this.size = capacity;
        this.table = new Customer[capacity];
        this.count = 0;
    }

    // --- The Hashing Function: h(k) = k mod m ---

    /**
     * Calculates the table index for a customer ID.
     *
     * Division method:
     * h(k) = k mod m
     *
     * @param key customer ID
     * @return index in the hash table
     */
    private int hash(int key) {
        return key % size;
    }

    private double getLoadFactor() {
        return (double) count / size;
    }

    private void resize() {
        Customer[] oldTable = table;

        size = size*2;
        table = new Customer[size];
        count = 0;

        for (Customer customer : oldTable) {
            if (customer != null) {
                put(customer);
            }
        }
        System.out.println("[REHASH] Customer table resized to " + size);
    }


    // --- Insert a Customer using Linear Probing ---
    /**
     * Inserts a customer into the hash table.
     *
     * If the calculated index is already occupied, linear probing checks
     * the next slots until an empty slot is found.
     *
     * Duplicate customer IDs are rejected to avoid overwriting existing data.
     *
     * @param customer customer to insert
     */
    public void put(Customer customer) {
        if (customer == null) return;

        int key = customer.getId();
        int index = hash(key);
        int startIndex = index;

        // Linear Probing: Look for an empty slot or update matching ID
        while (table[index] != null) {
            if (table[index].getId() == key) {
                System.out.println("[HASH WARNING] Customer ID " + key + " already exists. Insertion skipped.");
                return;
            }
            index = (index + 1) % size; // Move to the next index (circular array boundary)

            // Guard: the Table is completely full
            if (index == startIndex) {
                System.out.println("[ERROR] Hash Table is full! Could not insert customer: " + customer.getName());
                return;
            }
        }

        table[index] = customer; // Found an empty slot!
        count++;
        if (getLoadFactor() >= 0.7) {
            resize();
        }
    }

    // --- Retrieve a Customer using Linear Probing ---
    /**
     * Searches for a customer by ID.
     *
     * Uses the same hash function and linear probing path as insertion.
     *
     * @param key customer ID to search for
     * @return matching Customer or null if not found
     */
    public Customer get(int key) {
        int index = hash(key);
        int startIndex = index;

        while (table[index] != null) {
            if (table[index].getId() == key) {
                return table[index]; // Target found!
            }
            index = (index + 1) % size; // Continue linear probing search

            if (index == startIndex) break; // Traversed the whole table
        }

        return null; // Customer not found
    }

    // Debugging print to see inside the slots
    /**
     * Prints all hash table slots for debugging and demonstration.
     *
     * This helps to show where customers are stored and how collisions
     * are resolved by linear probing.
     */
    public void printTable() {
        System.out.println("\n--- Customer Hash Table Slots ---");
        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                System.out.println("Slot " + i + " -> [" + table[i] + "]");
            } else {
                System.out.println("Slot " + i + " -> [EMPTY]");
            }
        }
    }

    public int getCustomerCount() {
        return count;
    }

}
package structures;

import model.Customer;

public class CustomerHashTable {
    private Customer[] table;
    private int size; // This is 'm' from your lecture

    public CustomerHashTable(int capacity) {
        this.size = capacity;
        this.table = new Customer[capacity];
    }

    // --- The Hashing Function: h(k) = k mod m ---
    private int hash(int key) {
        return key % size;
    }

    // --- Insert a Customer using Linear Probing ---
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
    }

    // --- Retrieve a Customer using Linear Probing ---
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
}
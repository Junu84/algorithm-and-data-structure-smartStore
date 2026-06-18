package structures;

import model.Customer;

public class CustomerHashTable {
    private Customer[] table;
    private int size;

    public CustomerHashTable(int capacity) {
        this.table = new Customer[capacity];
        this.size = 0;
    }

    // Hash function based on the Customer's ID
    private int hash(int key) {
        return Math.abs(key) % table.length;
    }

    public void put(Customer customer) {
        if (customer == null) return;

        // Optional: Implement resizing if the table gets too full (e.g., size >= table.length)

        int index = hash(customer.getId());
        int startIndex = index;

        // Linear Probing: find an empty slot or update existing key
        while (table[index] != null) {
            // If customer with same ID already exists, update it or skip duplicates
            if (table[index].getId() == customer.getId()) {
                table[index] = customer; // Update
                return;
            }
            // Move to the next index circularly
            index = (index + 1) % table.length;

            // If we wrapped all the way around, the table is full
            if (index == startIndex) {
                System.out.println("Hash Table is Full!");
                return;
            }
        }

        // Insert into the open slot found
        table[index] = customer;
        size++;
    }

    public Customer get(int id) {
        int index = hash(id);
        int startIndex = index;

        while (table[index] != null) {
            if (table[index].getId() == id) {
                return table[index]; // Found it!
            }
            index = (index + 1) % table.length;

            if (index == startIndex) {
                break; // Wrapped around
            }
        }
        return null; // Not found
    }

    public int getCustomerCount() {
        return this.size;
    }

    public void printTable() {
        System.out.println("--- Customer Hash Table ---");
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                System.out.println("Slot " + i + ": [ID=" + table[i].getId() + ", Name=" + table[i].getName() + "]");
            } else {
                System.out.println("Slot " + i + ": [Empty]");
            }
        }
    }
}
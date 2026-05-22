package model;

public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity; // oder 'stock', je nachdem was deine anderen Klassen nutzen

    // S1 Konstruktor mit Attribut-Validierung
    public Product(int id, String name, double price, int quantity) {
        // S1 Validierung: Prüfen auf negative Werte
        if (price < 0 || quantity < 0) {
            System.out.println("[VALIDATION ERROR] Cannot create product '" + name + "' (ID: " + id + "). Price (" + price + ") and Stock/Quantity (" + quantity + ") cannot be negative!");
            // Setze Standardwerte oder wirf eine Exception, um das Objekt als "ungültig" zu markieren
            this.id = id;
            this.name = name + " [INVALID]";
            this.price = 0.0;
            this.quantity = 0;
            return;
        }

        // Gültige Zuweisung
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getter für M2 (Vergleich nach ID)
    public int getId() {
        return id;
    }

    // Getter für M3 (Quicksort-Vergleich nach Preis)
    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() { // passe den Namen ggf. an getStock() an
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            System.out.println("[VALIDATION ERROR] Quantity cannot be negative.");
            return;
        }
        this.quantity = quantity;
    }

    // Schöne Formatierung für System.out.println() in der Konsole
    @Override
    public String toString() {
        return String.format("ID: %-3d | Name: %-20s | Price: %6.2f€ | Stock: %d pcs.",
                id, name, price, quantity);
    }
}
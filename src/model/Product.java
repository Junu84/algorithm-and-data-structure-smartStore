package model;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;

    // Konstruktor, den deine Main-Klasse zum Erstellen der Testprodukte nutzt
    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Getter für M2 (Vergleich nach ID)
    public int getId() {
        return id;
    }

    // Getter für M3 (Quicksort-Vergleich nach Preis)
    public double getPrice() {
        return price;
    }

    // Weitere Getter für die Anzeige in der Main
    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    // Schöne Formatierung für System.out.println() in der Konsole
    @Override
    public String toString() {
        return String.format("ID: %-3d | Name: %-20s | Price: %6.2f€ | Stock: %d pcs.",
                id, name, price, stock);
    }
}
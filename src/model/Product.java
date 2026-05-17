package model;

public class Product {

        private final int id;
        private final String name;
        private final double price;
        private int stock;

        public Product(int id, String name, double price, int stock) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.stock = stock;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public double getPrice() { return price; }
        public int getStock() { return stock; }

        public void setStock(int stock) { this.stock = stock; }

        @Override
        public String toString() {
            return String.format("ID: %d | Name: %-15s | Price: %6.2f€ | Stock: %d pcs.", id, name, price, stock);
        }
    }


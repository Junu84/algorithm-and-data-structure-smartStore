package structures;

import model.Product;

public class ProductNode {
    private Product product;
    private ProductNode left;
    private ProductNode right;

    public ProductNode(Product product) {
        this.product = product;
        this.left = null;
        this.right = null;
    }

    public Product getProduct() {
        return this.product;
    }

    public int getId() {
        return this.product.getId();
    }

    public ProductNode getLeft() { return left; }
    public void setLeft(ProductNode left) { this.left = left; }

    public ProductNode getRight() { return right; }
    public void setRight(ProductNode right) { this.right = right; }
}
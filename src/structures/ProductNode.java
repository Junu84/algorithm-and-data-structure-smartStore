package structures;

import model.Product;

public class ProductNode {
    private final Product product;
    private ProductNode left;
    private ProductNode right;

    public ProductNode(Product product) {
        this.product = product;
        this.left = null;
        this.right = null;
    }

    public int getKey() {
        return product.getId();
    }

    public Product getProduct() { return product; }
    public ProductNode getLeft() { return left; }
    public ProductNode getRight() { return right; }

    public void setLeft(ProductNode left) { this.left = left; }
    public void setRight(ProductNode right) { this.right = right; }
}
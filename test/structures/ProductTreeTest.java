package structures;

import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTreeTest {

    private ProductTree tree;
    private Product rootProduct;
    private Product leftProduct;
    private Product rightProduct;

    @BeforeEach
    void setUp() {
        tree = new ProductTree();

        // Setup products with all 4 required parameters: id, name, price, stock
        rootProduct = new Product(10, "Laptop", 999.99, 10);
        leftProduct = new Product(5, "Mouse", 24.95, 50);
        rightProduct = new Product(15, "Keyboard", 59.90, 30);
    }
    @Test
    void testInsertAndStructure() {
        // Assert the tree starts completely empty
        assertNull(tree.getRoot(), "Tree should have no root initially.");

        // Insert root
        tree.insert(rootProduct);
        assertNotNull(tree.getRoot());
        assertEquals(10, tree.getRoot().getKey(), "Root node key should match inserted product ID.");

        // Insert children
        tree.insert(leftProduct);
        tree.insert(rightProduct);

        // Verify the binary search tree property (smaller left, larger right)
        assertNotNull(tree.getRoot().getLeft(), "Left child should exist.");
        assertEquals(5, tree.getRoot().getLeft().getKey());

        assertNotNull(tree.getRoot().getRight(), "Right child should exist.");
        assertEquals(15, tree.getRoot().getRight().getKey());
    }

    @Test
    void testLookup() {
        tree.insert(rootProduct);
        tree.insert(leftProduct);
        tree.insert(rightProduct);

        // Test looking up the root node
        ProductNode foundRoot = tree.lookup(10);
        assertNotNull(foundRoot);
        assertEquals("Laptop", foundRoot.getProduct().getName());

        // Test looking up a leaf node
        ProductNode foundLeft = tree.lookup(5);
        assertNotNull(foundLeft);
        assertEquals("Mouse", foundLeft.getProduct().getName());

        // Edge Case: Search for an ID that does not exist
        assertNull(tree.lookup(999), "Looking up a non-existent key should safely return null.");
    }

    @Test
    void testEmptyTreeLookup() {
        // Edge Case: Ensure lookup in an empty tree does not throw a NullPointerException
        assertNull(tree.lookup(10), "Searching an empty tree must return null instead of crashing.");
    }

    @Test
    void testDuplicateInsertionGrace() {
        tree.insert(rootProduct); // ID 10

        // Insert product with duplicate ID (should trigger your warning print and not change structure)
        Product duplicateProduct = new Product(10, "Other Laptop", 899.99, 5);

        assertDoesNotThrow(() -> tree.insert(duplicateProduct), "Inserting a duplicate ID should be handled gracefully.");

        // Ensure the original product is still the one stored
        assertEquals("Laptop", tree.getRoot().getProduct().getName(), "The original product should not be overwritten corruptly.");
    }

    @Test
    void testPrintInOrder() {
        tree.insert(rootProduct);
        tree.insert(leftProduct);

        // Ensure traversal runs without any runtime exception
        assertDoesNotThrow(() -> tree.printInOrder(), "printInOrder should execute smoothly.");
    }
}
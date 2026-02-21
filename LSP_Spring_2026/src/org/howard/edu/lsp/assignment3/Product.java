package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Represents a single product record in the ETL pipeline.
 *
 * <p>This class encapsulates all product attributes — ID, name, price,
 * category, and price range — behind private fields with public accessors.
 * Instances are created by {@link ProductExtractor} after parsing a CSV row,
 * mutated in-place by {@link ProductTransformer}, and serialised to CSV by
 * {@link ProductLoader}.</p>
 *
 * <p><strong>OO Principle – Encapsulation:</strong> all fields are private;
 * external code accesses state only through getters and setters.</p>
 *
 * @author Gerald Amoafo
 * @version 1.0
 */
public class Product {

    /** Unique numeric identifier parsed from the source CSV. */
    private int productId;

    /** Product name, trimmed and upper-cased during extraction. */
    private String name;

    /**
     * Product price as a {@link BigDecimal} to preserve decimal accuracy.
     * Updated by {@link ProductTransformer} after discount and rounding.
     */
    private BigDecimal price;

    /**
     * Product category trimmed from the source CSV.
     * May be upgraded to {@code "Premium Electronics"} by the transformer.
     */
    private String category;

    /**
     * Price-range label assigned by the transformer:
     * {@code Low}, {@code Medium}, {@code High}, or {@code Premium}.
     */
    private String priceRange;

    /**
     * Constructs a {@code Product} with the four fields parsed directly from
     * a CSV row.  {@code priceRange} is intentionally left {@code null} until
     * set by the transformer.
     *
     * @param productId numeric product identifier
     * @param name      product name (already trimmed and upper-cased)
     * @param price     product price (not yet discounted or rounded)
     * @param category  product category (already trimmed)
     */
    public Product(int productId, String name, BigDecimal price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    /**
     * Returns the product's numeric identifier.
     *
     * @return product ID
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Sets the product's numeric identifier.
     *
     * @param productId the new product ID
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Returns the product name (trimmed, upper-cased).
     *
     * @return product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the product name.
     *
     * @param name the new product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the product price.
     *
     * @return product price as a {@link BigDecimal}
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the product price.
     *
     * @param price the new price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Returns the product category.
     *
     * @return category string
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the product category.
     *
     * @param category the new category string
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns the price-range label assigned by the transformer.
     *
     * @return price range ({@code Low}, {@code Medium}, {@code High},
     *         or {@code Premium})
     */
    public String getPriceRange() {
        return priceRange;
    }

    /**
     * Sets the price-range label.
     *
     * @param priceRange the price-range label
     */
    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    /**
     * Returns a CSV-formatted string for this product, matching the output
     * column order expected by {@link ProductLoader}:
     * {@code ProductID,Name,Price,Category,PriceRange}.
     *
     * @return CSV row string
     */
    public String toCSVRow() {
        return productId + "," + name + "," + price.toString() + "," + category + "," + priceRange;
    }
}

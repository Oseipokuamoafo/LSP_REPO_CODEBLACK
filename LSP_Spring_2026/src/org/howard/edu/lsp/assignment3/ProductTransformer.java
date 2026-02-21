package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Applies all business-logic transformations to a list of {@link Product}
 * objects in-place.
 *
 * <p>This class implements the <em>Transform</em> phase of the ETL pipeline.
 * Transformation rules (identical to Assignment 2, applied in this order):</p>
 * <ol>
 *   <li><strong>Discount</strong> – Electronics products receive a 10 % price
 *       reduction ({@code price × 0.90}).</li>
 *   <li><strong>Rounding</strong> – The price is rounded to 2 decimal places
 *       using {@link RoundingMode#HALF_UP}.</li>
 *   <li><strong>Category upgrade</strong> – Electronics products whose
 *       post-discount, post-rounded price exceeds {@code $500.00} have their
 *       category changed to {@code "Premium Electronics"}.</li>
 *   <li><strong>Price range</strong> – A textual label is assigned based on
 *       the final price:
 *       <ul>
 *         <li>{@code Low}     – price ≤ $10.00</li>
 *         <li>{@code Medium}  – $10.00 &lt; price ≤ $100.00</li>
 *         <li>{@code High}    – $100.00 &lt; price ≤ $500.00</li>
 *         <li>{@code Premium} – price &gt; $500.00</li>
 *       </ul>
 *   </li>
 * </ol>
 *
 * <p><strong>OO Principle – Inheritance:</strong> extends {@link ETLStage},
 * providing a concrete implementation of {@link #execute()}.</p>
 *
 * <p><strong>OO Principle – Polymorphism:</strong> overrides the abstract
 * {@link ETLStage#execute()} method so that the correct transform logic
 * is dispatched when this stage is invoked through an {@code ETLStage}
 * reference.</p>
 *
 * @author Gerald Amoafo
 * @version 1.0
 */
public class ProductTransformer extends ETLStage {

    /** The list of products to transform, shared with the rest of the pipeline. */
    private final List<Product> products;

    /**
     * Constructs a {@code ProductTransformer} that will mutate the given
     * product list.
     *
     * @param products the list of {@link Product} objects to transform
     *                 (modified in-place during {@link #execute()})
     */
    public ProductTransformer(List<Product> products) {
        super("Transformer");
        this.products = products;
    }

    /**
     * Applies all four transformation rules to every {@link Product} in the
     * list, in the order required to replicate Assignment 2's output exactly.
     *
     * <p>This override does not perform any I/O and therefore never throws
     * {@link IOException}; the declaration is present only to satisfy the
     * {@link ETLStage} contract.</p>
     *
     * @throws IOException not thrown by this implementation
     */
    @Override
    public void execute() throws IOException {
        for (Product product : products) {
            applyElectronicsDiscount(product);
            roundPrice(product);
            upgradePremiumCategory(product);
            assignPriceRange(product);
        }
    }

    /**
     * Applies a 10 % discount to the price of Electronics products.
     *
     * <p>The check uses the product's original category string (trimmed during
     * extraction) and is case-insensitive, exactly matching Assignment 2's
     * {@code category.equalsIgnoreCase("Electronics")} guard.</p>
     *
     * @param product the product to process
     */
    private void applyElectronicsDiscount(Product product) {
        if (product.getCategory().equalsIgnoreCase("Electronics")) {
            product.setPrice(product.getPrice().multiply(new BigDecimal("0.90")));
        }
    }

    /**
     * Rounds the product's price to 2 decimal places using
     * {@link RoundingMode#HALF_UP}.
     *
     * @param product the product to process
     */
    private void roundPrice(Product product) {
        product.setPrice(product.getPrice().setScale(2, RoundingMode.HALF_UP));
    }

    /**
     * Upgrades the category of an Electronics product to
     * {@code "Premium Electronics"} if its post-discount, post-rounded price
     * exceeds {@code $500.00}.
     *
     * <p>The category check is performed before the category upgrade, so the
     * comparison uses the original category value — matching Assignment 2's
     * use of the {@code isElectronics} boolean that was captured before any
     * mutation.</p>
     *
     * @param product the product to process
     */
    private void upgradePremiumCategory(Product product) {
        if (product.getCategory().equalsIgnoreCase("Electronics")
                && product.getPrice().compareTo(new BigDecimal("500.00")) > 0) {
            product.setCategory("Premium Electronics");
        }
    }

    /**
     * Assigns a price-range label to the product based on its final price.
     *
     * <ul>
     *   <li>{@code Low}     – price ≤ $10.00</li>
     *   <li>{@code Medium}  – $10.00 &lt; price ≤ $100.00</li>
     *   <li>{@code High}    – $100.00 &lt; price ≤ $500.00</li>
     *   <li>{@code Premium} – price &gt; $500.00</li>
     * </ul>
     *
     * @param product the product to process
     */
    private void assignPriceRange(Product product) {
        BigDecimal price = product.getPrice();
        String priceRange;

        if (price.compareTo(new BigDecimal("10.00")) <= 0) {
            priceRange = "Low";
        } else if (price.compareTo(new BigDecimal("100.00")) <= 0) {
            priceRange = "Medium";
        } else if (price.compareTo(new BigDecimal("500.00")) <= 0) {
            priceRange = "High";
        } else {
            priceRange = "Premium";
        }

        product.setPriceRange(priceRange);
    }
}

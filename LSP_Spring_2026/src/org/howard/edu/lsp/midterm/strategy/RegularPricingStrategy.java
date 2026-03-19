package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for regular customers. No discount is applied.
 *
 * @author Gerald Amoafo
 */
public class RegularPricingStrategy implements PricingStrategy {

    /**
     * Returns the price unchanged (no discount for regular customers).
     *
     * @param price the base price
     * @return the original price with no discount applied
     */
    @Override
    public double calculatePrice(double price) {
        return price;
    }
}

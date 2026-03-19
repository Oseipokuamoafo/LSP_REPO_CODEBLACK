package org.howard.edu.lsp.midterm.strategy;

/**
 * Strategy interface for pricing calculations.
 * Implementations define how a final price is computed for a given base price.
 *
 * @author Gerald Amoafo
 */
public interface PricingStrategy {

    /**
     * Calculates the final price after applying the strategy's discount or pricing rule.
     *
     * @param price the base price before any discount
     * @return the final price after applying this strategy
     */
    double calculatePrice(double price);
}

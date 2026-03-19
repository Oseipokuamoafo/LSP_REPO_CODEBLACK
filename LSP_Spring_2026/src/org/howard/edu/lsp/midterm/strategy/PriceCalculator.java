package org.howard.edu.lsp.midterm.strategy;

/**
 * Calculates final prices using an injected PricingStrategy.
 * Delegates the pricing logic to the strategy, supporting open/closed extension.
 *
 * @author Gerald Amoafo
 */
public class PriceCalculator {

    private PricingStrategy strategy;

    /**
     * Constructs a PriceCalculator with the given pricing strategy.
     *
     * @param strategy the PricingStrategy to use for price calculations
     */
    public PriceCalculator(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Sets a new pricing strategy.
     *
     * @param strategy the PricingStrategy to apply
     */
    public void setStrategy(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Calculates the final price by delegating to the current pricing strategy.
     *
     * @param price the base price
     * @return the final price after applying the strategy
     */
    public double calculatePrice(double price) {
        return strategy.calculatePrice(price);
    }
}

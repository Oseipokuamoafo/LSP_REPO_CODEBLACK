package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for VIP customers. Applies a 20% discount.
 *
 * @author Gerald Amoafo
 */
public class VIPPricingStrategy implements PricingStrategy {

    /**
     * Applies a 20% discount to the base price.
     *
     * @param price the base price
     * @return the price after a 20% discount
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.80;
    }
}

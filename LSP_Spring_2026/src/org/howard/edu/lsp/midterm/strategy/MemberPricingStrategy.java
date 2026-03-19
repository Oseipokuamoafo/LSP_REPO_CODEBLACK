package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for member customers. Applies a 10% discount.
 *
 * @author Gerald Amoafo
 */
public class MemberPricingStrategy implements PricingStrategy {

    /**
     * Applies a 10% discount to the base price.
     *
     * @param price the base price
     * @return the price after a 10% discount
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.90;
    }
}

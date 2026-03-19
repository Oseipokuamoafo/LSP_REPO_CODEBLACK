package org.howard.edu.lsp.midterm.strategy;

/**
 * Driver class demonstrating the Strategy Pattern for price calculation.
 *
 * @author Gerald Amoafo
 */
public class Driver {

    /**
     * Entry point. Demonstrates pricing for REGULAR, MEMBER, VIP, and HOLIDAY customers.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        double basePrice = 100.0;

        PriceCalculator calculator = new PriceCalculator(new RegularPricingStrategy());
        System.out.println("REGULAR: " + calculator.calculatePrice(basePrice));

        calculator.setStrategy(new MemberPricingStrategy());
        System.out.println("MEMBER: " + calculator.calculatePrice(basePrice));

        calculator.setStrategy(new VIPPricingStrategy());
        System.out.println("VIP: " + calculator.calculatePrice(basePrice));

        calculator.setStrategy(new HolidayPricingStrategy());
        System.out.println("HOLIDAY: " + calculator.calculatePrice(basePrice));
    }
}

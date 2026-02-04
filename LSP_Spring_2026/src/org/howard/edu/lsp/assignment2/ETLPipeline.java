package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ETLPipeline {

    public static void main(String[] args) {
        String inputPath = "data/products.csv";
        String outputPath = "data/transformed_products.csv";

        int rowsRead = 0;
        int rowsSkipped = 0;
        int rowsWritten = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {

            // Always write header
            writer.write("ProductID,Name,Price,Category,PriceRange");
            writer.newLine();

            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue; // skip header row
                }

                if (line.trim().isEmpty()) {
                    rowsRead++;
                    rowsSkipped++;
                    continue;
                }

                rowsRead++;
                String[] parts = line.split(",");

                if (parts.length != 4) {
                    rowsSkipped++;
                    continue;
                }

                try {
                    int productId = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim().toUpperCase();
                    BigDecimal price = new BigDecimal(parts[2].trim());
                    String category = parts[3].trim();

                    boolean isElectronics = category.equalsIgnoreCase("Electronics");

                    // Apply 10% discount if Electronics
                    if (isElectronics) {
                        price = price.multiply(new BigDecimal("0.90"));
                    }

                    // Round price half-up to 2 decimals
                    price = price.setScale(2, RoundingMode.HALF_UP);

                    // Change category if Premium Electronics
                    if (isElectronics && price.compareTo(new BigDecimal("500.00")) > 0) {
                        category = "Premium Electronics";
                    }

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

                    writer.write(productId + "," + name + "," + price.toString() + "," + category + "," + priceRange);
                    writer.newLine();
                    rowsWritten++;

                } catch (NumberFormatException e) {
                    rowsSkipped++;
                }
            }

            writer.flush();

        } catch (IOException e) {
            System.out.println("Error: Input file not found or cannot be read: " + inputPath);
            return;
        }

        System.out.println("ETL Run Summary");
        System.out.println("----------------");
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsWritten);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output written to: " + outputPath);
    }
}

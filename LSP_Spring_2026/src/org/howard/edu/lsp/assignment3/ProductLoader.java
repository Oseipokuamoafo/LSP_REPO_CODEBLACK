package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Writes a list of transformed {@link Product} objects to a CSV output file.
 *
 * <p>This class implements the <em>Load</em> phase of the ETL pipeline.
 * It creates (or overwrites) the output file, writes a fixed header row, then
 * writes one CSV row per product using {@link Product#toCSVRow()}.  The number
 * of rows successfully written is tracked and exposed via
 * {@link #getRowsWritten()}.</p>
 *
 * <p>Output column order (identical to Assignment 2):</p>
 * <pre>ProductID,Name,Price,Category,PriceRange</pre>
 *
 * <p><strong>OO Principle – Inheritance:</strong> extends {@link ETLStage},
 * providing a concrete implementation of {@link #execute()} for the Load
 * phase.</p>
 *
 * <p><strong>OO Principle – Encapsulation:</strong> the output path, product
 * list, and row counter are private; callers interact only through
 * {@link #execute()} and {@link #getRowsWritten()}.</p>
 *
 * @author Gerald Amoafo
 * @version 1.0
 */
public class ProductLoader extends ETLStage {

    /** List of transformed products to write to the output file. */
    private final List<Product> products;

    /** Path to the CSV output file. */
    private final String outputPath;

    /** Number of product rows successfully written (excluding the header). */
    private int rowsWritten = 0;

    /**
     * Constructs a {@code ProductLoader} that will write the given products
     * to the specified output file.
     *
     * @param products   the list of transformed {@link Product} objects to write
     * @param outputPath path to the output CSV file
     *                   (e.g. {@code "data/transformed_products.csv"})
     */
    public ProductLoader(List<Product> products, String outputPath) {
        super("Loader");
        this.products = products;
        this.outputPath = outputPath;
    }

    /**
     * Writes all products to the output CSV file.
     *
     * <p>The method always writes the header row first:
     * {@code ProductID,Name,Price,Category,PriceRange}.
     * It then iterates the product list and writes each product's CSV row,
     * flushing the writer before closing.  This matches Assignment 2's
     * explicit {@code writer.flush()} call exactly.</p>
     *
     * @throws IOException if the output file cannot be created or written to
     */
    @Override
    public void execute() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {

            writer.write("ProductID,Name,Price,Category,PriceRange");
            writer.newLine();

            for (Product product : products) {
                writer.write(product.toCSVRow());
                writer.newLine();
                rowsWritten++;
            }

            writer.flush();
        }
    }

    /**
     * Returns the number of product rows written to the output file
     * (the header row is not counted).
     *
     * @return rows-written count
     */
    public int getRowsWritten() {
        return rowsWritten;
    }
}

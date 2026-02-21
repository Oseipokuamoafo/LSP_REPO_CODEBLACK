package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Extracts product records from a CSV file and converts them into
 * {@link Product} objects.
 *
 * <p>This class implements the <em>Extract</em> phase of the ETL pipeline.
 * It reads {@code data/products.csv} line by line, skips the header row,
 * validates each data row, and parses valid rows into {@link Product}
 * instances.  Rows that are empty, have the wrong number of fields, or
 * contain non-numeric values for {@code ProductID} or {@code Price} are
 * counted as skipped.</p>
 *
 * <p><strong>OO Principle – Inheritance:</strong> extends {@link ETLStage},
 * inheriting the stage name and the {@link #execute()} contract.</p>
 *
 * <p><strong>OO Principle – Encapsulation:</strong> the parsed product list
 * and counters are private; callers retrieve them only through the provided
 * accessor methods after {@link #execute()} completes.</p>
 *
 * <p>Extraction rules (identical to Assignment 2):</p>
 * <ul>
 *   <li>The first line (header) is always skipped without incrementing
 *       any counter.</li>
 *   <li>Blank lines increment both {@code rowsRead} and
 *       {@code rowsSkipped}.</li>
 *   <li>Lines with a column count other than 4 increment {@code rowsRead}
 *       and {@code rowsSkipped}.</li>
 *   <li>Lines whose {@code ProductID} or {@code Price} cannot be parsed
 *       increment {@code rowsRead} and {@code rowsSkipped}.</li>
 *   <li>All other lines produce a {@link Product} and increment
 *       {@code rowsRead} only.</li>
 * </ul>
 *
 * @author Gerald Amoafo
 * @version 1.0
 */
public class ProductExtractor extends ETLStage {

    /** Path to the CSV input file. */
    private final String inputPath;

    /** Accumulates successfully parsed {@link Product} objects. */
    private final List<Product> products = new ArrayList<>();

    /** Total number of data rows encountered (blank and invalid rows included,
     *  header excluded). */
    private int rowsRead = 0;

    /** Number of rows skipped due to being blank, malformed, or unparseable. */
    private int rowsSkipped = 0;

    /**
     * Constructs a {@code ProductExtractor} that will read from the given
     * CSV file path.
     *
     * @param inputPath path to the input CSV file (e.g. {@code "data/products.csv"})
     */
    public ProductExtractor(String inputPath) {
        super("Extractor");
        this.inputPath = inputPath;
    }

    /**
     * Opens the CSV file, reads every data row, validates it, and populates
     * the internal product list.
     *
     * <p>The parsing logic mirrors Assignment 2 exactly:</p>
     * <ol>
     *   <li>Skip the first (header) row.</li>
     *   <li>For each subsequent row: if blank, increment both counters and
     *       continue.</li>
     *   <li>Otherwise increment {@code rowsRead}; split on {@code ","};
     *       skip if not exactly 4 fields.</li>
     *   <li>Attempt to parse {@code ProductID} as {@code int} and
     *       {@code Price} as {@link BigDecimal}; skip on
     *       {@link NumberFormatException}.</li>
     *   <li>Trim whitespace from all fields; upper-case the name.</li>
     * </ol>
     *
     * @throws IOException if the file cannot be opened or read
     */
    @Override
    public void execute() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {

                // Always skip the header row without counting it
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                // Blank lines: count as read and skipped
                if (line.trim().isEmpty()) {
                    rowsRead++;
                    rowsSkipped++;
                    continue;
                }

                rowsRead++;
                String[] parts = line.split(",");

                // Must have exactly 4 fields
                if (parts.length != 4) {
                    rowsSkipped++;
                    continue;
                }

                try {
                    int productId       = Integer.parseInt(parts[0].trim());
                    String name         = parts[1].trim().toUpperCase();
                    BigDecimal price    = new BigDecimal(parts[2].trim());
                    String category     = parts[3].trim();

                    products.add(new Product(productId, name, price, category));

                } catch (NumberFormatException e) {
                    rowsSkipped++;
                }
            }
        }
    }

    /**
     * Returns the list of {@link Product} objects successfully parsed from
     * the CSV file.  Call this after {@link #execute()} completes.
     *
     * @return unmodifiable view of the parsed products (in source order)
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Returns the total number of data rows read from the file, not counting
     * the header.
     *
     * @return rows-read count
     */
    public int getRowsRead() {
        return rowsRead;
    }

    /**
     * Returns the number of rows that were skipped because they were blank,
     * had the wrong number of fields, or contained unparseable values.
     *
     * @return rows-skipped count
     */
    public int getRowsSkipped() {
        return rowsSkipped;
    }
}

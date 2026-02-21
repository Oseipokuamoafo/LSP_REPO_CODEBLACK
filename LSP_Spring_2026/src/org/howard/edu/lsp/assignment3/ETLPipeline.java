package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.util.List;

/**
 * Main entry point and orchestrator for the Assignment 3 ETL pipeline.
 *
 * <p>This class wires together the three ETL stages in the correct order:</p>
 * <ol>
 *   <li>{@link ProductExtractor} – reads and parses {@code data/products.csv}</li>
 *   <li>{@link ProductTransformer} – applies business rules to each product</li>
 *   <li>{@link ProductLoader} – writes results to
 *       {@code data/transformed_products.csv}</li>
 * </ol>
 *
 * <p>After all stages complete successfully, a run summary is printed to
 * standard output.  The summary format, file paths, and all transformation
 * logic are identical to Assignment 2's {@code ETLPipeline} class, ensuring
 * byte-for-byte equivalent output.</p>
 *
 * <p><strong>OO Principle – Polymorphism:</strong> each stage is invoked by
 * calling {@link ETLStage#execute()}, which dispatches to the concrete
 * implementation at runtime.  The pipeline is extensible — a new stage can
 * be introduced by subclassing {@link ETLStage} without modifying this
 * class.</p>
 *
 * @author Gerald Amoafo
 * @version 1.0
 */
public class ETLPipeline {

    /** Relative path to the input CSV file. */
    private static final String INPUT_PATH  = "data/products.csv";

    /** Relative path to the output CSV file. */
    private static final String OUTPUT_PATH = "data/transformed_products.csv";

    /**
     * Application entry point.
     *
     * <p>Instantiates and runs the three ETL stages in sequence.  If the
     * input file cannot be opened, an error message is printed and the
     * program exits immediately (matching Assignment 2 behaviour).  Write
     * failures are also reported and cause an early exit.</p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // --- Extract phase ---
        ProductExtractor extractor = new ProductExtractor(INPUT_PATH);
        try {
            extractor.execute();
        } catch (IOException e) {
            System.out.println("Error: Input file not found or cannot be read: " + INPUT_PATH);
            return;
        }

        // --- Transform phase ---
        List<Product> products = extractor.getProducts();
        ProductTransformer transformer = new ProductTransformer(products);
        try {
            transformer.execute();
        } catch (IOException e) {
            // ProductTransformer.execute() performs no I/O; this branch is unreachable
        }

        // --- Load phase ---
        ProductLoader loader = new ProductLoader(products, OUTPUT_PATH);
        try {
            loader.execute();
        } catch (IOException e) {
            System.out.println("Error: Cannot write to output file: " + OUTPUT_PATH);
            return;
        }

        // --- Summary (identical format to Assignment 2) ---
        System.out.println("ETL Run Summary");
        System.out.println("----------------");
        System.out.println("Rows read: "        + extractor.getRowsRead());
        System.out.println("Rows transformed: " + loader.getRowsWritten());
        System.out.println("Rows skipped: "     + extractor.getRowsSkipped());
        System.out.println("Output written to: " + OUTPUT_PATH);
    }
}

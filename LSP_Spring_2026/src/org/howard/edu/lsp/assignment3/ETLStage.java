package org.howard.edu.lsp.assignment3;

import java.io.IOException;

/**
 * Abstract base class for all stages of the ETL pipeline.
 *
 * <p>Every stage — Extract, Transform, and Load — inherits from this class.
 * It provides a common identity ({@link #getStageName()}) and enforces the
 * {@link #execute()} contract that each concrete stage must implement.</p>
 *
 * <p><strong>OO Principle – Inheritance:</strong>
 * {@link ProductExtractor}, {@link ProductTransformer}, and
 * {@link ProductLoader} all extend this class, inheriting the
 * {@code stageName} field and its accessor without code duplication.</p>
 *
 * <p><strong>OO Principle – Polymorphism:</strong>
 * {@code execute()} is declared {@code abstract} here so that each subclass
 * supplies its own implementation.  A caller holding an {@code ETLStage}
 * reference can invoke {@code execute()} without knowing the concrete type,
 * letting the correct stage-specific logic run at runtime.</p>
 *
 * @author Gerald Amoafo
 * @version 1.0
 */
public abstract class ETLStage {

    /** Human-readable label identifying this stage (e.g. "Extractor"). */
    private final String stageName;

    /**
     * Constructs an {@code ETLStage} with the given name.
     *
     * @param stageName label for this stage; used in logging and diagnostics
     */
    protected ETLStage(String stageName) {
        this.stageName = stageName;
    }

    /**
     * Returns the human-readable name of this ETL stage.
     *
     * @return stage name string
     */
    public String getStageName() {
        return stageName;
    }

    /**
     * Executes this stage's primary operation.
     *
     * <p>Concrete subclasses override this method to implement their
     * stage-specific work: reading from a CSV file, transforming product
     * data in memory, or writing results to an output file.</p>
     *
     * @throws IOException if an I/O error occurs during execution
     *                     (not all subclasses will throw this)
     */
    public abstract void execute() throws IOException;
}

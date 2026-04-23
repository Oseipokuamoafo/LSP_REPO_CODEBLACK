package org.howard.edu.lsp.finalexam.question2;

/**
 * Abstract base class defining the Template Method for generating reports.
 * The generateReport() method defines the fixed workflow; subclasses
 * override loadData(), formatHeader(), formatBody(), and formatFooter().
 */
public abstract class Report {

    /** Loads report-specific data. Must be called before formatting. */
    protected abstract void loadData();

    /** Returns the formatted header string for this report. */
    protected abstract String formatHeader();

    /** Returns the formatted body string for this report. */
    protected abstract String formatBody();

    /** Returns the formatted footer string for this report. */
    protected abstract String formatFooter();

    /**
     * Template method — defines the fixed report generation workflow.
     * Subclasses customize each step without changing this sequence.
     */
    public final void generateReport() {
        loadData();
        System.out.println("=== HEADER ===");
        System.out.println(formatHeader());
        System.out.println();
        System.out.println("=== BODY ===");
        System.out.println(formatBody());
        System.out.println();
        System.out.println("=== FOOTER ===");
        System.out.println(formatFooter());
        System.out.println();
    }
}

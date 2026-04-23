package org.howard.edu.lsp.finalexam.question2;

/**
 * Abstract base class defining the Template Method pattern for report generation.
 * The generateReport() method defines the fixed workflow; subclasses implement the variable steps.
 */
public abstract class Report {

    /** Template method — defines the fixed report generation workflow. */
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
    }

    /** Loads report-specific data. Must be implemented by subclasses. */
    protected abstract void loadData();

    /** Returns the formatted header string. */
    protected abstract String formatHeader();

    /** Returns the formatted body string. */
    protected abstract String formatBody();

    /** Returns the formatted footer string. */
    protected abstract String formatFooter();
}

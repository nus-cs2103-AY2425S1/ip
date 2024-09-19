package atreides.ui;

/**
 * Custom exception class for handling errors specific to the Atreides application.
 * Provides a detailed error message for debugging purposes.
 */
public class AtreidesException extends Exception {
    private final String description;

    /**
     * Constructs a new AtreidesException with the specified error description.
     *
     * @param description The detailed error description to be associated with this exception.
     */
    public AtreidesException(String description) {
        super(description);
        this.description = description;
    }

    public String getDescription() {
        return "Error: " + description + "\n";
    }
}

package exception;

/**
 * The {@code AlphaException} class represents a custom exception specific to the Alpha application.
 * This exception is thrown when a particular error condition is encountered in the application.
 */
public class AlphaException extends Exception {
    
    /** A detailed description of the exception. */
    private final String description;
    
    /**
     * Constructs a new {@code AlphaException} with the specified detail message.
     *
     * @param description the detail message that explains the reason for the exception
     */
    public AlphaException(String description) {
        this.description = description;
    }
    
    /**
     * Returns the description of this exception.
     *
     * @return the detail message string of this {@code AlphaException} instance
     */
    @Override
    public String getMessage() {
        return this.description;
    }
}

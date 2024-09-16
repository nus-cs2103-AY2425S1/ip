package exception;

/**
 * Represents an exception specific to the Primo application.
 * This exception is used to signal errors or invalid conditions
 * that occur within the application.
 */
public class PrimoException extends Exception {

    private String message;

    /**
     * Constructs a new PrimoException with the specified detail message.
     *
     * @param message The detail message to be used for this exception.
     */
    public PrimoException(String message) {
        this.message = message;
    }

    /**
     * Returns the detail message string of this PrimoException.
     *
     * @return The detail message string of this exception.
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Returns a string representation of this PrimoException.
     * The string representation contains the detail message.
     *
     * @return A string representation of this exception.
     */
    @Override
    public String toString() {
        return message;
    }
}

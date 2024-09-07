package jeff.exception;

/**
 * Represents an exception for any errors in this package.
 */
public class JeffException extends Exception {
    private String message;

    /**
     * Constructor that creates a JeffException object with a specific message.
     *
     * @param message that describes the error
     */
    public JeffException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Constructor that creates a JeffException object with a general message.
     */
    public JeffException() {
        super();
        this.message = "Sorry, I don't know what this means.";
    }

    /**
     * Returns the message of the error.
     *
     * @return the error message
     */
    @Override
    public String toString() {
        return this.message;
    }
}

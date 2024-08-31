package muller.command;

/**
 * Custom exception class for handling errors specific to the Muller application.
 */
public class MullerException extends Exception {
    /**
     * Constructs a new MullerException with the specified detail message.
     *
     * @param message The detail message.
     */
    public MullerException(String message) {
        super(message);
    }
}


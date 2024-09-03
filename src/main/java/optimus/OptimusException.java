package optimus;

/**
 * Custom exception class for handling errors specific to the Optimus application.
 * {@code OptimusException} is used to indicate application-specific issues that need to be
 * reported to the user.
 */
public class OptimusException extends Exception {

    /**
     * Constructs a new {@code OptimusException} with the specified detail message.
     *
     * @param message the detail message, saved for later retrieval by the {@link #getMessage()} method.
     */
    public OptimusException(String message) {
        super(message);
    }
}

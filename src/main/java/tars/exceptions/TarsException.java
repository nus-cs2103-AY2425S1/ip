package tars.exceptions;

/**
 * Represents a custom runtime exception for the TARS chatbot.
 *
 * <p>The {@code TarsException} class is a custom exception that extends
 * {@link RuntimeException}. It is used to handle specific errors and
 * exceptions that occur within the TARS chatbot.</p>
 */
public class TarsException extends RuntimeException {

    /**
     * Constructs a new {@code TarsException} with the specified detail message.
     *
     * @param str The detail message explaining the reason for the exception.
     */
    public TarsException(String str) {
        super(str);
    }
}

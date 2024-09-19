package Gary;

/**
 * The {@code GaryException} class represents a custom exception for the Gary application.
 * This exception is thrown when an invalid command or input is encountered.
 */
public class GaryException extends Exception {

    /**
     * Constructs a new {@code GaryException} with the specified detail message.
     *
     * @param message The detail message for the exception.
     */
    public GaryException(String message) {
        super(message);

        // Assertion: Ensure that the message is not null or empty
        assert message != null && !message.isEmpty() : "GaryException must have a non-null, non-empty message";
    }
}

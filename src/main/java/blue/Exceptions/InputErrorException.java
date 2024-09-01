package blue.Exceptions;

/**
 * Thrown to indicate that there is an error with the user's input, such as incorrect formatting or missing information.
 */
public class InputErrorException extends Exception {

    /**
     * Constructs an {@code InputErrorException} with a default detail message.
     * The default message is "Erm...What the sigma are you on about?".
     */
    public InputErrorException() {
        super("Erm...What the sigma are you on about?");
    }

    /**
     * Constructs an {@code InputErrorException} with the specified detail message.
     *
     * @param message The detail message, saved for later retrieval by the {@link Throwable#getMessage()} method.
     */
    public InputErrorException(String message) {
        super(message);
    }
}

package exceptions;

/**
 * The {@code InvalidFormatException} class represents an exception that is thrown
 * when an input provided by the user does not adhere to the expected format.
 * This exception extends {@code DownyException}.
 */
public class InvalidFormatException extends DownyException {

    /**
     * Constructs a new {@code InvalidFormatException} with the specified detail message.
     *
     * @param message The detail message, which provides more information about
     *                the format error that occurred.
     */
    public InvalidFormatException(String message) {
        super(message);
    }
}

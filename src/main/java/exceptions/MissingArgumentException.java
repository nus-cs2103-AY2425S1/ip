package exceptions;

/**
 * The {@code MissingArgumentException} class represents an exception that is thrown
 * when a required argument is missing from a command input provided by the user.
 * This exception extends {@code DownyException}.
 */
public class MissingArgumentException extends DownyException {

    /**
     * Constructs a new {@code MissingArgumentException} with the specified detail message.
     *
     * @param message The detail message, which provides more information about
     *                the missing argument error that occurred.
     */
    public MissingArgumentException(String message) {
        super(message);
    }
}

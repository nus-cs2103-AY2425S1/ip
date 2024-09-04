package exceptions;

/**
 * The {@code DownyException} class represents a custom exception that is thrown
 * in the application when an error specific to the application's logic occurs.
 * It extends the {@code Exception} class, allowing it to be used as a checked exception.
 */
public class DownyException extends Exception {

    /**
     * Constructs a new {@code DownyException} with the specified detail message.
     *
     * @param message The detail message, which provides more information about the error that occurred.
     */
    public DownyException(String message) {
        super(message);
    }

}

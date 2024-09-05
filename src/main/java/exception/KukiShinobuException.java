package exception;

/**
 * Constructs a {@code FileNotFoundKukiShinobuException} with a default error message.
 * The message indicates that the file could not be found and suggests checking the file path.
 */
public class KukiShinobuException extends Exception {

    /**
     * Constructs a {@code KukiShinobuException} with the specified detail message.
     *
     * @param message The detail message which is saved for later retrieval by the
     *                {@link Throwable#getMessage()} method.
     */
    public KukiShinobuException(String message) {
        super(message);
    }
}

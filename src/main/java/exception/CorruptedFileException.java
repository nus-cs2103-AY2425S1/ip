package exception;

/**
 * Represents an exception that is thrown when a file is corrupted.
 */
public class CorruptedFileException extends DashException {

    /**
     * Constructs a CorruptedFileException with the specified detail message.
     *
     * @param message The detail message, which is saved for later retrieval by the {@link #getMessage()} method.
     */
    public CorruptedFileException(String message) {
        super(message);
    }
}

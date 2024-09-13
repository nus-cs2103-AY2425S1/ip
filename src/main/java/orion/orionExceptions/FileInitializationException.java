package orion.orionexceptions;

/**
 * Custom exception class representing an error that occurs during file
 * initialization.
 *
 * @author Pradyumna
 */
public class FileInitializationException extends OrionException {

    /**
     * Constructs a new FileInitializationException with the specified error
     * message.
     *
     * @param message the detail message. The detail message is saved for later
     *                retrieval by the {@link #getMessage()} method.
     */
    public FileInitializationException(String message) {
        super("File Initialization Error: " + message);
    }
}

package mylo.storage;

/**
 * Exception thrown when a file is found to be corrupted or not in the expected format.
 * <p></p>
 * <p>This exception indicates that an operation related to file loading cannot be completed
 * due to unexpected content in the file.</p>
 *
 * @author cweijin
 */
public class FileCorruptedException extends Exception {

    /**
     * Constructs a {@code FileCorruptedException} with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public FileCorruptedException(String message) {
        super(message);
    }
}

package mylo.storage;

/**
 * Signals that there were errors converting and/or storing data to a file.
 * <p></p>
 * <p>This exception indicates issues that occur during file operations, such as
 * saving or loading data from the storage file.</p>
 *
 * @author cweijin
 */
public class StorageOperationException extends Exception {

    /**
     * Constructs a {@code StorageOperationException} with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public StorageOperationException(String message) {
        super(message);
    }
}

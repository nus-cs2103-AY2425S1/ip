package storage;

/**
 * Signals that there were errors converting and/or storing data to file.
 */
public class StorageOperationException extends Exception {
    public StorageOperationException(String message) {
        super(message);
    }
}

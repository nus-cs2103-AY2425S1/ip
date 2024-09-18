package yapmeister;

/**
 * Represents an Exception caused by an error in Storage functions
 */
public class StorageException extends Exception {
    public StorageException(String message) {
        super(message);
    }
}

package cloud.exception;

/**
 * Represents an exception for handling Storage related errors.
 */
public class StorageException extends CloudException {
    public StorageException(String message) {
        super(message);
    }
}

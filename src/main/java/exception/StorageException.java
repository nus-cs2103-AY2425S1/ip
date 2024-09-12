package exception;

/**
 * Exception the thrown by Storage
 */
public class StorageException extends ToMoException {
    /**
     * Constructor of StorageException
     * 
     * @param message The error message
     */
    public StorageException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "StorageException: " + getMessage();
    }
}

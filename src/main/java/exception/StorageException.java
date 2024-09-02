package exception;

public class StorageException extends ToMoException {
    public StorageException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "StorageException: " + getMessage();
    }
}

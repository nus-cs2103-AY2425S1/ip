public class StorageException extends ToMoException {
    StorageException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "StorageException: " + getMessage();
    }
}

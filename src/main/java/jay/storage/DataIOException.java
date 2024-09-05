package jay.storage;

/**
 * Represents an exception thrown when there is an error in reading or writing data.
 */
public class DataIOException extends Exception {
    public DataIOException(String message) {
        super(message);
    }
}

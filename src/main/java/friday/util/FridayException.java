package friday.util;

/**
 * Represents an exception that is thrown for invalid inputs or other errors in the Friday application.
 */
public class FridayException extends RuntimeException {

    /**
     * Constructs a new FridayException with the specified detail message.
     *
     * @param msg The detail message.
     */
    public FridayException(String msg) {
        super(msg);
    }
}

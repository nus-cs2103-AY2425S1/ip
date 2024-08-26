package infinity.infinityexception;

/**
 * This class handles the exception when the input is not recognised.
 */
public class InfinityException extends Exception {
    /**
     * Constructor for InfinityException.
     * 
     * @param message The message to be displayed when the exception is thrown.
     */
    public InfinityException(String message) {
        super(message);
    }
}

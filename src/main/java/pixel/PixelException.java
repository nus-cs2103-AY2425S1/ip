package pixel;

/**
 * This class represents a custom exception for handling pixel-related errors.
 */
public class PixelException extends Exception {
    /**
     * Constructs a new PixelException with the specified error message.
     *
     * @param msg the error message
     */
    public PixelException(String msg) {
        super(msg);
    }
}

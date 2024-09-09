package gallium.main;


/**
 * Represents an exception specific to Gallium.
 * This exception is thrown when an error occurs within Gallium.
 */
public class GalliumException extends Exception {
    /**
     * Constructs a new GalliumException with a message.
     *
     * @param message The message that provides more information about the
     *                exception.
     */
    public GalliumException(String message) {
        super(message);
    }
}

package panorama.exception;

/**
 * Represents an custom exception specific to the Panorama application.
 *
 * This is used as a base class for all exceptions that are related to the Panorama application.
 */
public abstract class PanoramaException extends Exception {
    /**
     * Constructs a new {@code PanoramaException} with the specified error message.
     *
     * @param message the detail message associated with the exception
     */
    public PanoramaException(String message) {
        super(message);
    }
}

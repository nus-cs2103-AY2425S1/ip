package niko.common;

/**
 * Represents an exception specific to the Niko application.
 */
public class NikoException extends Exception {
    /**
     * Constructs a NikoException with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public NikoException(String message) {
        super(message);
    }
}

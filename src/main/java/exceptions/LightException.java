package exceptions;


/**
 * Represents an exception specific to the components.Light application.
 */
public class LightException extends Exception {
    private String message;

    /**
     * Creates a LightException object.
     *
     * @param message The error message.
     */
    public LightException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Returns the error message.
     *
     * @return The error message.
     */
    @Override
    public String toString() {
        return this.message;
    }
}

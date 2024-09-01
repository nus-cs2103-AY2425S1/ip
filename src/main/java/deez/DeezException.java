package deez;

/**
 * Exception class for Deez.
 */
public class DeezException extends RuntimeException {
    /**
     * Array of error messages associated with this exception.
     */
    protected String[] errorMessages;

    /**
     * Constructor for DeezException. Takes in an array of error messages.
     *
     * @param errorMessage Array of error messages.
     */
    public DeezException(String... errorMessage) {
        super();
        this.errorMessages = errorMessage;
    }

    /**
     * Gets the error messages associated with this exception.
     *
     * @return The array of error messages.
     */
    public String[] getErrorMessages() {
        return this.errorMessages;
    }
}

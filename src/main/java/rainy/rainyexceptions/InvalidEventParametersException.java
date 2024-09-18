package rainy.rainyexceptions;

/**
 * Occurs when user inputs an invalid event parameter.
 */
public class InvalidEventParametersException extends Exception {

    /**
     * Constructs a new <code>InvalidEventParameterException</code> to be thrown.
     * @param errorMessage  Displays message when the exception is thrown.
     */
    public InvalidEventParametersException(String errorMessage) {
        super(errorMessage);
    }
}

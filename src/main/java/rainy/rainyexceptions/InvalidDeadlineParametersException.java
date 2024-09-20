package rainy.rainyexceptions;

/**
 * Occurs when user inputs an invalid deadline parameter.
 */
public class InvalidDeadlineParametersException extends Exception {

    /**
     * Constructs a new <code>InvalidDeadlineParameterException</code> to be thrown.
     * @param errorMessage  Displays message when the exception is thrown.
     */
    public InvalidDeadlineParametersException(String errorMessage) {
        super(errorMessage);
    }
}

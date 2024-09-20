package exceptions;

/**
 * The TooManyParametersException is thrown when a command receives more parameters than expected.
 * This exception helps ensure that commands are used correctly with the intended number of parameters.
 */
public class TooManyParametersException extends Exception {

    /**
     * Constructs a new TooManyParametersException with the specified detail message.
     */
    public TooManyParametersException() {
        super("One input at a time!");
    }
}

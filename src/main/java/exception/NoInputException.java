package exception;

/**
 * NoInputException class is used to indicate that no input is given to UI,
 * which is an undefined behaviour.
 */
public class NoInputException extends DPlusPlusEException {
    /**
     * Constructor for NoInputException.
     */
    public NoInputException() {
        super(String.format("no input is given"));

    }
}

package dgpt.exception;

/**
 * Thrown to indicate that the number of inputs after the command is incorrect.
 */
public class IncorrectInputException extends Exception {

    public IncorrectInputException(String message) {
        super(message);
    }
}

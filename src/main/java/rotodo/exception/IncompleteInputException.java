package rotodo.exception;

/**
 * The IncompleteInputException class encapsulates
 * the specific InvalidInputException where there are
 * insufficient arguments in the user input.
 *
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public class IncompleteInputException extends InvalidInputException {
    public IncompleteInputException(String arg) {
        super(arg);
    }
}

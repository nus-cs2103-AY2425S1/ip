package david.exceptions;

/**
 * Invalid deadline exception class for David class
 */
public class DavidInvalidDeadlineException extends DavidException {

    public DavidInvalidDeadlineException() {}

    @Override
    public String toString() {
        return "Please enter a valid deadline after \"by\".";
    }
}

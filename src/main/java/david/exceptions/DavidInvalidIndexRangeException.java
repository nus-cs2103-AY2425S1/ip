package david.exceptions;


/**
 * Invalid index range exception class for David class
 */
public class DavidInvalidIndexRangeException extends DavidException {
    public DavidInvalidIndexRangeException() {}

    @Override
    public String toString() {
        return "The index you entered is invalid. Please make sure you enter a valid index between 1 and the "
                + "maximum number in the list.";
    }
}

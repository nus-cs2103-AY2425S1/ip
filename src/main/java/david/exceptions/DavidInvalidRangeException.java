package david.exceptions;

/**
 * Invalid task exception class for David class
 */
public class DavidInvalidRangeException extends DavidException {

    public DavidInvalidRangeException() {}

    @Override
    public String toString() {
        return "Please enter a valid range using \"/from\" and \"/to\".";
    }
}

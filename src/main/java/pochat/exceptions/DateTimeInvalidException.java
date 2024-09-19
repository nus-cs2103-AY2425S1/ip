package pochat.exceptions;

/**
 * Throws when the datetime is invalid
 */
public class DateTimeInvalidException extends Exception {
    /**
     * Informs the user what went wrong (datetime is invalid)
     */
    public DateTimeInvalidException() {
        super("Datetime is of an invalid format! Please enter all datetime as dd/mm/yyyy HHMM");
    }
}

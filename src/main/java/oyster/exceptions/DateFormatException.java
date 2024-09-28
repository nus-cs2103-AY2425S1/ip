package oyster.exceptions;

/**
 * Exception when Date is inputted in wrong format.
 */
public class DateFormatException extends OysterException {
    public DateFormatException() {
        super(String.format("[%s] %s", "Invalid date", "Please input a valid date in (dd/mm/yyyy) format!"));
    }
}

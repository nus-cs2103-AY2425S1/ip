package echoa;

/**
 * This class encapsulates time formatting errors.
 */

public class DateFormatException extends EchoaException {
    public DateFormatException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Date should be formatted as YYYY-MM-DD\n";
    }
}

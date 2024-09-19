package echoa.exception;

/**
 * This class encapsulates time formatting errors.
 * It extends from EchoaException.
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

package echoa.exception;

/**
 * This class encapsulates date formatting errors.
 * It extends from EchoaException.
 */
public class TimeFormatException extends EchoaException {
    public TimeFormatException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Time should be formatted as HH:MM\n";
    }
}

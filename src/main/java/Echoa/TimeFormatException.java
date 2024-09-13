package echoa;

/**
 * This class encapsulates date formatting errors.
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

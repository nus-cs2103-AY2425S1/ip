package count.exception;

/**
 * InvalidTimelineException is thrown during Event object creation
 * when the start date/time is later (or same as) than end date/time
 */
public class InvalidTimelineException extends CountException {
    public InvalidTimelineException(String msg) {
        super(msg);
    }
}

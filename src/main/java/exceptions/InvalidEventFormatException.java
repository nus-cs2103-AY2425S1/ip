package exceptions;

/**
 * Thrown when an invalid event string is parsed by Him.
 *
 * @author IsaacPangTH
 */
public class InvalidEventFormatException extends HimException {

    /**
     * Constructor for<code>InvalidEventFormatException</code>.
     */
    public InvalidEventFormatException() {
        super("Events need a description, a start time and an end time!\n" +
                "Use the format: \"event [description] /start [start date] /at [start time] " +
                "/end [end date] /at [end time]\"");
    }
}

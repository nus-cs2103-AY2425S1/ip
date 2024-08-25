package exception.parse;

/**
 * An Exception for when users did not indicate an end time for an Event Task.
 *
 * @author Toh Yi Hui A0259080A
 */
public class MissingEndTimeException extends ParseException {
    /**
     * Constructor for a new MissingEndTimeException.
     *
     * @param sample the sample for creating a new Event Task.
     */
    public MissingEndTimeException(String sample) {
        super("Please indicate an end time for the event.\nFor e.g. " + sample);
    }
}

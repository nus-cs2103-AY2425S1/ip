package yihuibot.exception.parse;

/**
 * An Exception for when users did not indicate a start time for an Event Task.
 *
 * @author Toh Yi Hui A0259080A
 */
public class MissingStartTimeException extends ParseException {
    /**
     * Constructor for a new MissingStartTimeException.
     *
     * @param sample the sample for how an Event Task is to be created.
     */
    public MissingStartTimeException(String sample) {
        super("Please indicate a start time for the event.\nFor e.g. " + sample);
    }
}

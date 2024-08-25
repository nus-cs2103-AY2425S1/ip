package exception.parse;

/**
 * An Exception for when users did not include a description for the Task.
 *
 * @author Toh Yi Hui A0259080A
 */
public class MissingDescriptionException extends ParseException {
    /**
     * Constructor for a MissingDescriptionException.
     *
     * @param sample the sample for how to create a new Task.
     */
    public MissingDescriptionException(String sample) {
        super("Please indicate a description for the task.\nFor e.g. " + sample);
    }
}

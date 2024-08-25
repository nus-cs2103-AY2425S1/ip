package exception.parse;

/**
 * An Exception for when users did not input a deadline to a Deadline task.
 *
 * @author Toh Yi Hui A0259080A
 */
public class MissingDeadlineException extends ParseException {
    /**
     * Constructor for a new MissingDeadlineException.
     *
     * @param sample the sample for how to create a Deadline Task.
     */
    public MissingDeadlineException(String sample) {
        super("Please indicate a deadline for the task.\nFor e.g. " + sample);
    }
}

package exception.parse;

/**
 * An Exception for when user indicated an end time before start time for an Event.
 *
 * @author Toh Yi Hui A0259080A
 */
public class IncorrectArrangementException extends ParseException {
    /**
     * Constructor for an IncorrectArrangementException.
     *
     * @param sample a sample of how the event command should be called.
     */
    public IncorrectArrangementException(String sample) {
        super("Please indicate a start time before an end time.\nFor e.g. " + sample);
    }
}

package ahmad.exceptions.deadline;

import ahmad.exceptions.AhmadException;

/**
 * Exception class for invalid deadline times.
 */
public class DeadlineInvalidTimeException extends AhmadException {
    /**
     * Constructs a DeadlineInvalidTimeException instance based on given time.
     *
     * @param time The invalid time in question.
     */
    public DeadlineInvalidTimeException(String time) {
        super(time + " is not a valid deadline time");
    }
}

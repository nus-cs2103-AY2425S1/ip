package ahmad.exceptions.deadline;

import ahmad.exceptions.AhmadException;

public class DeadlineInvalidArgsException extends AhmadException {
    public DeadlineInvalidArgsException() {
        super("That is not a valid \"deadline\" command");
    }
}

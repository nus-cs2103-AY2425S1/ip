package ahmad.exceptions.deadline;

import ahmad.exceptions.AhmadException;

public class DeadlineEmptyNameException extends AhmadException {
    public DeadlineEmptyNameException() {
        super("Name should not be empty");
    }
}

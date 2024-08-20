package exception;

public class DeadlineEmptyException extends EchoBotException{
    public DeadlineEmptyException() {
        super("task.Deadline cannot be left empty!");
    }

    public DeadlineEmptyException(String message) {
        super(message);
    }
}

package exception;

public class TaskNameEmptyException extends EchoBotException {
    public TaskNameEmptyException() {
        super("OOPS!!! The task name could not be left empty!!!");
    }

    public TaskNameEmptyException(String message) {
        super(message);
    }
}

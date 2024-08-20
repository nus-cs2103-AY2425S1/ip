package exception;

public class EventStartEndDateEmptyException extends EchoBotException {
    public EventStartEndDateEmptyException() {
        super("task.Event start date and end date must both be stated!!!");
    }

    public EventStartEndDateEmptyException(String message) {
        super(message);
    }
}

package echobot.exception;

/**
 * Represents a TaskNotFoundException.
 */
public class TaskNotFoundException extends EchoBotException {
    public TaskNotFoundException() {
        super("Cannot find this task in the list!");
    }

    public TaskNotFoundException(String message) {
        super(message);
    }
}

package echoa;

/**
 * This class encapsulates errors relating to update instructions.
 * It has 3 subclasses:
 * ToDoUpdateFormatException, DeadlineUpdateFormatException, EventUpdateFormatException.
 */

public abstract class UpdateFormatException extends EchoaException {
    public UpdateFormatException() {
        super();
    }

    public abstract String getMessage();
}

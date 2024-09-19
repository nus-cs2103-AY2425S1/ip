package echoa.exception;

/**
 * This class encapsulates errors relating to update instructions.
 * It extends from EchoaExcpetion.
 * It has subclasses:
 * ToDoUpdateFormatException, DeadlineUpdateFormatException, EventUpdateFormatException.
 */
public abstract class UpdateFormatException extends EchoaException {
    public UpdateFormatException() {
        super();
    }

    @Override
    public abstract String getMessage();
}

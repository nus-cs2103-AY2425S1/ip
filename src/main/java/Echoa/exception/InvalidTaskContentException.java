package echoa.exception;

/**
 * InvalidTaskContentException is a class that encapsulates errors relating to task.
 * It extends from class EchoaException.
 * It has subclasses:
 * InvalidToDoContextException, InvalidDeadlineContentException and InvalidEventContentException.
 */
public abstract class InvalidTaskContentException extends EchoaException {
    public InvalidTaskContentException() {
        super();
    }

    @Override
    public abstract String getMessage();
}

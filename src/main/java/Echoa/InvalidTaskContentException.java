package echoa;

/**
 * InvalidTaskContentException is a class that encapsulates errors relating to task.
 * It extends from class EchoaException.
 */

public abstract class InvalidTaskContentException extends EchoaException {
    public InvalidTaskContentException() {
        super();
    }

    @Override
    public abstract String getMessage();
}

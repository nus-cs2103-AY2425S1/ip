package panorama.exception;

import panorama.Message;

/**
 * This exception is used to signal that a user-provided input for
 * an ID is not an integer.
 */
public class NonIntegerIdException extends PanoramaException {
    /**
     * Constructs a new {@code NonIntegerIdException} with a default error message.
     */
    public NonIntegerIdException() {
        super(Message.MyException.NON_INTEGER);
    }
}

package panorama.exception;

import panorama.Message;

/**
 * Thrown to indicate that an unknown command has been issued.
 * This exception is used to signal that the user has entered a command
 * that is not recognized by the application.
 */
public class UnknownCommandException extends PanoramaException {
    /**
     * Constructs a new {@code UnknownCommandException} with a default error message.
     */
    public UnknownCommandException() {
        super(Message.MyException.UNKNOWN_COMMAND);
    }
}

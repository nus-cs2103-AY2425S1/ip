package panorama.exception;

import panorama.Message;

/**
 * Thrown to indicate that an invalid date has been specified.
 * This exception is used to signal that the date provided by the user
 * does not conform to the expected format or is otherwise invalid.
 */
public class InvalidUsageException extends PanoramaException {
    /**
     * Constructs a new {@code InvalidDateException} with a default error message.
     *
     * @param commandFormat The command format for the wrongly used command.
     */
    public InvalidUsageException(String commandFormat) {
        super(Message.MyException.INVALID_USAGE + " Command format: " + commandFormat);
    }
}

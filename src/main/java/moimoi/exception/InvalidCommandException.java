package moimoi.exception;

/**
 * Represents an exception related to invalid command input.
 */
public class InvalidCommandException extends MoiMoiException {

    /**
     * Returns corresponding error message.
     *
     * @return Error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "I couldn't understand that.. Try giving me a valid command!";
    }

}

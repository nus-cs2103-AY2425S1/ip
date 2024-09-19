package choaticbot.exceptions;

/**
 * The {@code UnknownActionException} class represents a specific exception that is thrown
 * when an unrecognized or invalid action is encountered in the ChoaticBot application.
 * It extends the {@link ChoaticBotException} class to handle cases of unknown actions.
 */
public class UnknownActionException extends ChoaticBotException {

    /**
     * Constructs an {@code UnknownActionException} with the specified error message.
     *
     * @param msg The error message that provides details about the unknown action exception.
     */
    public UnknownActionException(String msg) {
        super(msg);
    }
}

package choaticbot.exceptions;

/**
 * The {@code ChoaticBotException} class represents a custom exception specific
 * to the ChoaticBot application. It extends the {@link Exception} class and
 * provides a constructor to pass error messages.
 */
public class ChoaticBotException extends Exception {

    /**
     * Constructs a {@code ChoaticBotException} with the specified error message.
     *
     * @param msg The error message that provides details about the exception.
     */
    public ChoaticBotException(String msg) {
        super(msg);
    }
}

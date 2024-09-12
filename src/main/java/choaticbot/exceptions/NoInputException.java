package choaticbot.exceptions;

/**
 * The {@code NoInputException} class represents a specific exception that is thrown
 * when no input is provided to the ChoaticBot. It extends the {@link ChoaticBotException}
 * class to handle cases of missing input.
 */
public class NoInputException extends ChoaticBotException {

    /**
     * Constructs a {@code NoInputException} with the specified error message.
     *
     * @param msg The error message that provides details about the missing input exception.
     */
    public NoInputException(String msg) {
        super(msg);
    }
}

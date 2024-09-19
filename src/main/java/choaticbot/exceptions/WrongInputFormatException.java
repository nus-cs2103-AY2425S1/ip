package choaticbot.exceptions;

/**
 * Represents an exception thrown when an unrecognized or invalid input is entered by the user
 * in the ChoaticBot application. This exception indicates that the input format does not meet
 * the expected criteria.
 * <p>
 * This class extends {@link ChoaticBotException} to provide a specific type of exception for handling
 * wrong input formats.
 */
public class WrongInputFormatException extends ChoaticBotException {

    /**
     * The {@code WrongInputFormatException} class represents a specific exception that is thrown
     * when an unrecognized or invalid input is entered by the user in the ChoaticBot application.
     * It extends the {@link ChoaticBotException} class to handle cases of wrong input format.
     */
    public WrongInputFormatException(String msg) {
        super(msg);
    }
}

package choaticbot.exceptions;

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

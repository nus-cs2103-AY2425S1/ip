package exception;

/**
 * A custom exception for an invalid numerical input by the user
 */
public class CasperBotNumberFormatException extends CasperBotException {
    /**
     * Constructor for invalid number format exception
     */
    public CasperBotNumberFormatException() {
        super("Did not pass in a valid integer",
                "Ensure that you pass in an integer after \"mark\", \"unmark\" or \"delete\"");
    }
}

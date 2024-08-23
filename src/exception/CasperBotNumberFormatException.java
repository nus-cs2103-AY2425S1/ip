package exception;

public class CasperBotNumberFormatException extends CasperBotException {
    public CasperBotNumberFormatException() {
        super("Did not pass in a valid integer", "Ensure that you pass in an integer after \"mark\", \"unmark\" or \"delete\"");
    }
}

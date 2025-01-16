package exception;

/**
 * A custom exception for an IOException when handling a file
 */
public class CasperBotIoException extends CasperBotException {
    public CasperBotIoException() {
        super("There was an error reading/writing to the file.", "Restart the programme and try again!");
    }
}

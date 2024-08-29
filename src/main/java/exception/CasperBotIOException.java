package exception;

public class CasperBotIOException extends CasperBotException{
    /**
     * A custom exception for an IOException when handling a file
     */
    public CasperBotIOException() {
        super("There was an error reading/writing to the file.", "Restart the programme and try again!");
    }
}

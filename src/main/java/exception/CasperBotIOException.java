package exception;

public class CasperBotIOException extends CasperBotException{
    public CasperBotIOException() {
        super("There was an error reading/writing to the file.", "Restart the programme and try again!");
    }
}

package exception;

// used when user inputs an invalid command i.e. incorrect number of arguments
public class InvalidCommandException extends LBotException{
    public InvalidCommandException(String message) {
        super(message);
    }
}

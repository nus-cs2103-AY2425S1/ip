package LBot.exception;

// for errors that occur during execution of commands
public class ExecuteCommandException extends LBotException {
    public ExecuteCommandException(String message) {
        super(message);
    }
}

package exception;

// used when error encountered from parsing command
// e.g. datetime issue
public class ParseCommandException extends LBotException {
    public ParseCommandException(String message) {
        super(message);
    }
}

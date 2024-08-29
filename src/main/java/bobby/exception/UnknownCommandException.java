package bobby.exception;

public class UnknownCommandException extends BobbyException {
    public UnknownCommandException() {
        super("Please use a valid command.");
    }
}

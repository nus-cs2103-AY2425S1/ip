package nugget.exception;

public class UnknownCommandException extends NuggetException {
    public UnknownCommandException() {
        super("OOPS!!! Please enter valid commands!");
    }
}
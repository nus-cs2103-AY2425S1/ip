package exceptions;

public class UnknownCommandException extends PukeException {
    public UnknownCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}

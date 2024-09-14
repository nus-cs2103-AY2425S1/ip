package fred.Exceptions;

public class UnknownCommandException extends FredException {
    public UnknownCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}

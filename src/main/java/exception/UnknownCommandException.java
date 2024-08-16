package exception;

public class UnknownCommandException extends Exception {
    public UnknownCommandException(String command) {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}

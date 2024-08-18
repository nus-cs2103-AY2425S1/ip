package exceptions;

public class UnknownCommandException extends Exception {
    public UnknownCommandException() {
        super("Command not found!");
    }
}

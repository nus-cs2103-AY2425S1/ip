package cow.exceptions;

public class UnknownCommandException extends Exception {
    public UnknownCommandException() {
        super("Command not found!");
    }
}

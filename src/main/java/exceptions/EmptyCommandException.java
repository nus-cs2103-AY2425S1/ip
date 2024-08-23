package exceptions;

public class EmptyCommandException extends BottyException {
    public EmptyCommandException() {
        super("commands.Command cannot be empty!");
    }
}

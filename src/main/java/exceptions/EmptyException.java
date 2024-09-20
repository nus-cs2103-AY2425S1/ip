package exceptions;

public class EmptyException extends Throwable {
    public EmptyException(String command) {
        super("OOPS!!! The description of a " + command + " cannot be empty.\n");
    }
}

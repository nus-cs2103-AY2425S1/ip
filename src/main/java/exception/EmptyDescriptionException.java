package exception;

public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String command) {
        super("OOPS!!! The description of a " + command + " cannot be empty.");
    }
}

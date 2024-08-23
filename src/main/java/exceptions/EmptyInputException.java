package exceptions;

public class EmptyInputException extends PukeException {
    public EmptyInputException() {
        super("OOPS!!! You need to enter a command.");
    }

    public EmptyInputException(String message) {
        super(message);
    }
}

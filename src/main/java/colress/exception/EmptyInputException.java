package colress.exception;

public class EmptyInputException extends Exception {
    public EmptyInputException() {
        super("You need to input something. Try again.");
    }
}

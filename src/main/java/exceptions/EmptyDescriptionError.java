package exceptions;

public class EmptyDescriptionError extends Exception {
    public EmptyDescriptionError() {
        super("Empty description or incorrect format.");
    }
}

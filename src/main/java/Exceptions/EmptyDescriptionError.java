package Exceptions;

public class EmptyDescriptionError extends Exception {
    public EmptyDescriptionError() {
        super("Empty description.");
    }
}

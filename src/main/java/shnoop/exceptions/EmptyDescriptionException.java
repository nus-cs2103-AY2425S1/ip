package shnoop.exceptions;

public class EmptyDescriptionException extends Exception {

    public EmptyDescriptionException(String message) {
        super(message);
    }

    public EmptyDescriptionException() {
        super("✿ Shnoop ✿: There must be something in the water. Don't leave the description empty! Type something.");
    }
}

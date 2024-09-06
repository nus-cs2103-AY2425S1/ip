package shnoop.exceptions;

/**
 * Encapsulates the situation where user tries to create a Task without inputting a description.
 */
public class EmptyDescriptionException extends Exception {

    public EmptyDescriptionException(String message) {
        super(message);
    }

    public EmptyDescriptionException() {
        super("✿ Shnoop ✿: There must be something in the water. Don't leave the description empty! Type something.");
    }
}

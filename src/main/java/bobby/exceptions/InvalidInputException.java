package bobby.exceptions;

/**
 * The {@code InvalidInputException} class represents a specific type of {@code BobbyException}
 * that is thrown when the user provides an input that is not recognized as a valid command.
 */
public class InvalidInputException extends BobbyException {

    /**
     * Constructs a new {@code InvalidInputException} with a default detail message
     * indicating that the provided input is not recognized by the application.
     */
    public InvalidInputException() {
        super("I am sorry, but I do not know what that means!");
    }
}

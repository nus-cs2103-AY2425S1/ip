package noosy.exception;

/**
 * Represents a custom exception for the Noosy task management chatbot.
 * This exception is thrown when task information is incomplete within the Noosy application.
 */
public class NoosyIncompleteTaskException extends NoosyException {

    /**
     * Constructs a new NoosyException asking for task information.
     *
     */
    public NoosyIncompleteTaskException() {
        super("Okay surezies but what do you want to do?");
    }
}
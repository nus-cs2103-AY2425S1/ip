package noosy.exception;

/**
 * Represents a custom exception for the Noosy task management chatbot.
 * This exception is thrown when a duplicate task is getting added.
 */
public class NoosyDuplicateException extends NoosyException {

    /**
     * Constructs a new NoosyDuplicateException with the specified error message.
     *
     * @param errorMessage The detail message for this exception.
     */
    public NoosyDuplicateException() {
        super("A task with the same description has been added. Try checking!");
    }
}

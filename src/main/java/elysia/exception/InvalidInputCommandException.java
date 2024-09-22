package elysia.exception;

/**
 * Represents an invalid command exception.
 */
public class InvalidInputCommandException extends ElysiaException {
    /**
     * Constructs an {@code InvalidInputCommandException} with a message indicating that the format for the input
     * command is invalid.
     */
    public InvalidInputCommandException() {
        super("Oopsie! It looks like the format for this command is invalid.\n"
                + "Here are some examples of valid command:\n"
                + "todo read book\n"
                + "deadline return book \\by mon\n"
                + "event project meeting /from 23 Sep\\2pm /to 4pm");
    }
}

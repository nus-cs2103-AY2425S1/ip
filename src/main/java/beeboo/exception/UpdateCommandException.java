package beeboo.exception;

/**
 * Represents an exception that is thrown when an update command is incorrectly formatted
 * or contains invalid input in the BeeBoo task management chatbot.
 * This exception provides a custom error message with instructions on how to properly
 * format update commands for both events and deadlines.
 */
public class UpdateCommandException extends BeeBooExceptions {

    /**
     * Constructs a new {@code UpdateCommandException} with the specified detail message.
     *
     * @param error The detail message, which explains the reason for the exception.
     */
    public UpdateCommandException(String error) {
        super(error);
    }

    /**
     * Returns a string representation of this exception, including instructions
     * on how to correctly format update commands for tasks in the BeeBoo chatbot.
     *
     * @return A string containing error details and usage instructions for update commands.
     */
    @Override
    public String toString() {
        return "Your update commands are wrong. Here are the instructions on how to update tasks:\n"
                + "update [index] from [yyyy-mm-dd] [hh-mm]/to [time]\n (for event)"
                + "update [index] [yyyy-mm-dd] [hh-mm] (for deadline)";
    }
}

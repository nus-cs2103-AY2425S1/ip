package jackbean.exception;

/**
 * Represents an exception thrown when the user inputs an invalid task type in the JackBean chatbot.
 */
public class InvalidTaskTypeException extends Exception {
    /**
     * Constructs an InvalidTaskTypeException with a default message.
     * This JavaDoc was written by GitHub Copilot.
     */
    public InvalidTaskTypeException(String message) {
        super(message);
    }

    /**
     * Constructs an InvalidTaskTypeException with a default message.
     * This JavaDoc was written by GitHub Copilot.
     */
    public InvalidTaskTypeException() {
        super("Invalid jackbean.task.Task Type");
    }

    @Override
    public String toString() {
        return "Yo Homieee, i did not catch what you are saying!\nPlease use deadline, event, todo, "
                + "or other command formats, type help if you are unsure of the formats!";
    }
}

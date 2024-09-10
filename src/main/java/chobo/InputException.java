package chobo;
/**
 * Represents an exception thrown when there is invalid input in the Chobo chatbot.
 * This exception is used to handle various cases of incorrect or missing input.
 */
public class InputException extends Exception {
    /**
     * Instantiates a new Input exception.
     */
    public InputException() {
        super("invalid input");
    }

    /**
     * Constructs a new chobo.InputException with a specific error message
     * based on the type of input error encountered.
     *
     * @param msg The type of input error (e.g., "name", "todo", "event", "deadline", "id").
     */
    public InputException(String msg) {
        super(msg.equals("name") ? "pls include name of task"
                : msg.equals("todo") ? "todo format: todo xxxxx"
                : msg.equals("event") ? "event format: event xxxxx /from xxxxx /to xxxxx"
                : msg.equals("deadline") ? "deadline format: deadline xxxxx /by xxxx"
                : msg.equals("id") ? "invalid task ID"
                : msg.equals("find") ? "find format: find xxxxx"
                : msg.equals("duplicate") ? "duplicate task!"
                : "invalid input");
    }
}

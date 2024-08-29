package bobby.exception;

/**
 * EmptyDescriptionException is an exception thrown there is no description provided for the task.
 */
public class EmptyDescriptionException extends BobbyException {

    /**
     * Constructs a EmptyDescriptionException with the specified task type.
     *
     * @param task Type of task.
     */
    public EmptyDescriptionException(String task) {
        super("Please fill in the description in this format: " + task + " [desc]");
    }
}

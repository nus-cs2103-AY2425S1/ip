package exceptions;

/**
 * The EmptyTaskException class is a custom exception that is thrown when the user
 * attempts to add a task without providing a description.
 */
public class EmptyTaskException extends Exception {
    /**
     * Constructs a new EmptyTaskException with a detailed message indicating that the
     * description of the task is empty.
     *
     * @param task The type of task (e.g., "todo", "deadline", "event") for which the description is missing.
     */
    public EmptyTaskException(String task) {
        super(String.format("OOPS!! The description of a %s cannot be empty %n"
                             + "Have you forgotten to enter the details?", task));
    }
}

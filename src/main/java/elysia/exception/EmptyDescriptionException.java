package elysia.exception;

/**
 * Represents an empty description exception.
 */
public class EmptyDescriptionException extends ElysiaException {
    /**
     * Constructs an {@code EmptyDescriptionException} with a message indicating that the description for the specified
     * task type is missing.
     *
     * @param taskType the type of task for which the description is missing
     */
    public EmptyDescriptionException(String taskType) {
        super("Oopsie! It looks like the description for this "
                + taskType
                + " is missing.\n"
                + "How about we add a little something to it?");
    }
}

package elysia.exception;

/**
 * Represents an empty description exception.
 */
public class EmptyDescriptionException extends ElysiaException {
    public EmptyDescriptionException(String taskType) {
        super("Oopsie! It looks like the description for this " +
                taskType +
                " is missing.\n" +
                "How about we add a little something to it?");
    }
}

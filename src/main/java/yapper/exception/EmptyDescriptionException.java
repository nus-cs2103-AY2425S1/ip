package yapper.exception;

/**
 * Represents an exception that occurs when the description of a task is empty.
 */
public class EmptyDescriptionException extends YapperException {
    public EmptyDescriptionException(String commandType) {
        super("Eh yo The description of a " + commandType + " cannot be empty.");
    }
}

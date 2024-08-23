package exceptions;

public class EmptyDescriptionException extends PukeException {
    public EmptyDescriptionException(String taskType) {
        super("OOPS!!! The description of a " + taskType + " cannot be empty.");
    }
}

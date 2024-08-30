package duke.exception;

/**
 * Represents an exception that is thrown when the description of a command is empty.
 */
public class EmptyDescriptionException extends Exception {

    /**
     * Constructs an EmptyDescriptionException with a specific command name.
     *
     * @param command The name of the command whose description is empty.
     */
    public EmptyDescriptionException(String command) {
        super("OOPS!!! The description of a " + command + " cannot be empty.");
    }
}
package dude.exception;

/**
 * Thrown to indicate that no command description is provided in the input.
 */
public class DudeNullDescriptionException extends DudeException {

    /**
     * Constructs a new DudeNullDescriptionException with a detailed message
     * indicating that a description is required for the specified command.
     *
     * @param command The name of the command that requires a description.
     */
    public DudeNullDescriptionException(String command) {
        super("You need to add description(s) for \"" + command + "\".");
    }
}

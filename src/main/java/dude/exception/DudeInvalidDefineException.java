package dude.exception;

/**
 * Thrown to indicate that an invalid shortcut define is provided in the input.
 */
public class DudeInvalidDefineException extends DudeException {

    /**
     * Constructs a DudeInvalidDefineException with no detail message to indicate
     * that user attempts to define a pre-defined command as shortcut.
     */
    public DudeInvalidDefineException() {
        super("You can't define a command as shortcut.");
    }

    /**
     * Constructs a DudeInvalidDefineException with detail message to indicate
     * that user attempts to define shortcut for a not existing command.
     *
     * @param command The command that was attempted to be mapped to.
     */
    public DudeInvalidDefineException(String command) {
        super("\"" + command + "\" is not a valid command.");
    }
}

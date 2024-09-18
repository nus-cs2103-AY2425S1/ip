package exceptions;

/**
 * The NoLastCommandToUndo class is a custom exception that is thrown when the user
 * attempts to undo a command but there is none to undo
 */
public class NoLastCommandToUndo extends Exception {

    /**
     * Constructs a new NoLastCommand with a detailed message indicating that
     * the user hasn't entered any command to undo.
     */
    public NoLastCommandToUndo() {
        super("There is no last command to undo!");
    }
}

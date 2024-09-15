package exceptions;

public class NoLastCommandToUndo extends Exception {

    /**
     * Constructs a new NoLastCommand with a detailed message indicating that
     * the user hasn't entered any command to undo.
     */
    public NoLastCommandToUndo() {
        super("There is no last command to undo!");
    }
}

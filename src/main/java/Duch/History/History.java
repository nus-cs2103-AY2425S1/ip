package duch.history;

/**
 * Encapsulates a command history
 */
public abstract class History {
    /**
     * Contains the code to undo the command.
     */
    public abstract void undo();
}

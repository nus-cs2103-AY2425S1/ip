package rasputin.command;

/**
 * An interface which indicates whether a command is able to be undone.
 *
 */
public interface Undoable {
    public String undo();
}

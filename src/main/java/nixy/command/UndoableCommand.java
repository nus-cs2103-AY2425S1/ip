package nixy.command;

/**
 * UndoableCommand interface represents the different commands that the user can undo.
 */
public interface UndoableCommand extends Command {
    void undo();
}

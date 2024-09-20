package dude.exception;

/**
 * Thrown to indicate that a shortcut deletion was invalid.
 */
public class DudeShortcutDeleteException extends DudeException {

    /**
     * Constructs a DudeShortcutDeleteException with no detailed message to indicate
     * that user attempts to delete a pre-defined command.
     */
    public DudeShortcutDeleteException() {
        super("Don't you dare to delete a command type.");
    }

    /**
     * Constructs a DudeShortcutDeleteException with detailed message to indicate
     * that user attempts to delete a not-existing shortcut.
     *
     * @param shortcut The shortcut that was attempted to be deleted.
     */
    public DudeShortcutDeleteException(String shortcut) {
        super("\"" + shortcut + "\" is not even defined as a shortcut yet.");
    }
}

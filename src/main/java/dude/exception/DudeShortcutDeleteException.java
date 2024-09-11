package dude.exception;

public class DudeShortcutDeleteException extends DudeException {

    public DudeShortcutDeleteException() {
        super("You can't delete a command type.");
    }

    public DudeShortcutDeleteException(String shortcut) {
        super("\"" + shortcut + "\" is not defined as a valid shortcut yet.");
    }
}

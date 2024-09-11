package dude.exception;

public class DudeInvalidDefineException extends DudeException {

    public DudeInvalidDefineException() {
        super("You can't define a command as shortcut.");
    }

    public DudeInvalidDefineException(String command) {
        super("\"" + command + "\" is not a valid command.");
    }
}

package dude.exception;

public class DudeNullDescriptionException extends DudeException {
    public DudeNullDescriptionException(String s) {
        super("You need to add description for \"" + s + "\".");
    }
}

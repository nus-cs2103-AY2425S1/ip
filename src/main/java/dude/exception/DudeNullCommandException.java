package dude.exception;

public class DudeNullCommandException extends DudeException {
    public DudeNullCommandException() {
        super("You did not enter anything.");
    }
}

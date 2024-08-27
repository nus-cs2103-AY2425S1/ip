package dude.exception;

public class DudeNullDateTimeException extends DudeException {
    public DudeNullDateTimeException(String s) {
        super("You need to add date and time for \"" + s + "\".");
    }
}

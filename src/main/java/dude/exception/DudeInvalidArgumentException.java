package dude.exception;

public class DudeInvalidArgumentException extends DudeException {
    public DudeInvalidArgumentException(String command, String error, String expected) {
        super("\"" + error + "\" is not a valid format for \"" + command + "\", please use \"" + expected + "\".");
    }
}

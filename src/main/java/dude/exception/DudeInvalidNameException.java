package dude.exception;

public class DudeInvalidNameException extends DudeException {

    public DudeInvalidNameException() {
        super ("Don't put these characters in your task description: ( '|', '/' )");
    }
}

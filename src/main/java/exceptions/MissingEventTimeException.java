package exceptions;

public class MissingEventTimeException extends PukeException {
    public MissingEventTimeException() {
        super("OOPS!!! An event must have both start and end times specified.");
    }
}


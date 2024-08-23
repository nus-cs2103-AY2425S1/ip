package exceptions;

public class MissingTimeException extends PukeException {
    public MissingTimeException() {
        super("OOPS!!! The deadline must have a specified time.");
    }
}


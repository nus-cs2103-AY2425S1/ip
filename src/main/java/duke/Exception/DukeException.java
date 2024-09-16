package duke.Exception;

public class DukeException extends Exception {
    protected final String NEW_LINE = "\n";

    public DukeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return NEW_LINE + super.getMessage() + NEW_LINE;
    }
}

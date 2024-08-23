package exceptions;

public class MissingTaskNumberException extends PukeException {
    public MissingTaskNumberException(boolean isDone) {
        super("OOPS!!! You must specify a task number to " + (isDone ? "mark" : "unmark") + ".");
    }
}
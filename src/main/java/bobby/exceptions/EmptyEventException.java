package bobby.exceptions;

public class EmptyEventException extends BobbyException {
    public EmptyEventException() {
        super("The description, start time, or end time of an event cannot be empty.");
    }
}

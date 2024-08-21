public class EventNoDescriptionException extends TaskException {
    public EventNoDescriptionException() {
        super("The description of an event cannot be empty!");
    }
}

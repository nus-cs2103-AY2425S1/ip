public class EventException extends InputException {
    public EventException() {
        super("Incorrect usage of \"event\"\nCorrect usage: event<event_name");
    }
}

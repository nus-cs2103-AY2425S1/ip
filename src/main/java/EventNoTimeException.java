public class EventNoTimeException extends TestamentException {

    public EventNoTimeException() {
        super("You have not provided the time for this event.\nThe format for events is as follows:\n" +
                "event (details) /from (time) /to (time)");
    }

}

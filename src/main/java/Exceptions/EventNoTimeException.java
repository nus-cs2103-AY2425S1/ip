package Exceptions;

/**
 * An exception that occurs when an event description is missing the necessary dates
 */
public class EventNoTimeException extends TestamentException {

    /**
     * Constructor for EventNoTimeException
     */
    public EventNoTimeException() {
        super("You have not provided the time for this event.\nThe format for events is as follows:\n" +
                "event (details) /from (time) /to (time)");
    }

}

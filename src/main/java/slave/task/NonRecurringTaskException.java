package slave.task;

/**
 * This exception is thrown when the user tries to set a task object that is not a subclass of RecurringTypeTask
 * to be a recurring event.
 */
public class NonRecurringTaskException extends IllegalArgumentException {
    public NonRecurringTaskException() {
        super();
    }
}

package ChatterBoxErrors;

/**
 * Represents an error for creating Deadline tasks,
 */
public class ChatterBoxDeadlineError extends ChatterBoxError {
    /**
     * Initialisation of error for Deadline.
     */
    public ChatterBoxDeadlineError() {
        super("____________________________________________________________\n"
                + "deadline Usage: deadline {String taskName} /by {String endTime}\n"
                + "____________________________________________________________");
    }
}

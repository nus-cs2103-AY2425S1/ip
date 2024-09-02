package chatterboxerrors;

/**
 * Represents an error for creating Deadline tasks,
 */
public class ChatterBoxDeadlineError extends ChatterBoxError {
    /**
     * Initialisation of error for Deadline.
     */
    public ChatterBoxDeadlineError() {
        super("""
              ____________________________________________________________
              deadline Usage: deadline {String taskName} /by {String endTime}
              ____________________________________________________________""");
    }
}

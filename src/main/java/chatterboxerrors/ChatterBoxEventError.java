package chatterboxerrors;

/**
 * Represents an error for creating Event tasks.
 */
public class ChatterBoxEventError extends ChatterBoxError {
    /**
     * Represents an error for creating Event tasks.
     */
    public ChatterBoxEventError() {
        super("""
              ____________________________________________________________
              event Usage: event {String taskName} /from {String startTime} /to {String endTime}
              ____________________________________________________________""");
    }
}

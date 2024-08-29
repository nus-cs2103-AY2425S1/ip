package ChatterBoxErrors;

/**
 * Represents an error for the Mark command.
 */
public class ChatterBoxMarkError extends ChatterBoxError {
    /**
     * Initialisation of error for the Mark command.
     */
    public ChatterBoxMarkError() {
        super("____________________________________________________________\n"
                + "mark/ unmark Usage: mark/ unmark {Integer taskNo}\n"
                + "____________________________________________________________");
    }
}

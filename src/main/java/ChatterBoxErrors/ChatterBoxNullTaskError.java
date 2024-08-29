package ChatterBoxErrors;

/**
 * Represents an error when a task cannot be found.
 */
public class ChatterBoxNullTaskError extends ChatterBoxError {
    /**
     * Initialisation of error where task cannot be found.
     */
    public ChatterBoxNullTaskError() {
        super("____________________________________________________________\n"
                + "There is no such task in the list.\n"
                + "Use the list command to check available task.\n"
                + "____________________________________________________________");
    }
}

package ChatterBoxErrors;

/**
 * Represents an error for the Delete command.
 */
public class ChatterBoxDeleteError extends ChatterBoxError {
    /**
     * Initialisation of error for the Delete command.
     */
    public ChatterBoxDeleteError() {
        super("____________________________________________________________\n"
                + "delete Usage: delete {Integer taskNo}\n"
                + "____________________________________________________________");
    }
}

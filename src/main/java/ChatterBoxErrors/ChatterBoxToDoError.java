package ChatterBoxErrors;

/**
 * Represents an error for creating ToDo tasks.
 */
public class ChatterBoxToDoError extends ChatterBoxError {
    /**
     * Represents an error for creating ToDo tasks.
     */
    public ChatterBoxToDoError() {
        super("____________________________________________________________\n"
                + "todo Usage: todo {String taskName}\n"
                + "____________________________________________________________");
    }
}

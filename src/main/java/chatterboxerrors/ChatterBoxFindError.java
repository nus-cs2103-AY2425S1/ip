package chatterboxerrors;

/**
 * Represents an error for creating ToDo tasks.
 */
public class ChatterBoxFindError extends ChatterBoxError {
    /**
     * Represents an error for creating ToDo tasks.
     */
    public ChatterBoxFindError() {
        super("____________________________________________________________\n"
                + "todo Usage: todo {String taskName}\n"
                + "____________________________________________________________");
    }
}

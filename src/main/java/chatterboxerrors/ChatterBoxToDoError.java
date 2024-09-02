package chatterboxerrors;

/**
 * Represents an error for creating ToDo tasks.
 */
public class ChatterBoxToDoError extends ChatterBoxError {
    /**
     * Represents an error for creating ToDo tasks.
     */
    public ChatterBoxToDoError() {
        super("""
              ____________________________________________________________
              todo Usage: todo {String taskName}
              ____________________________________________________________""");
    }
}

package chatterboxerrors;

/**
 * Represents an error when a task cannot be found.
 */
public class ChatterBoxNullTaskError extends ChatterBoxError {
    /**
     * Initialisation of error where task cannot be found.
     */
    public ChatterBoxNullTaskError() {
        super("""
              ____________________________________________________________
              There is no such task in the list.
              Use the list command to check available task.
              ____________________________________________________________""");
    }
}

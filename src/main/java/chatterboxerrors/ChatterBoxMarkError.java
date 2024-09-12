package chatterboxerrors;

/**
 * Represents an error for the Mark command.
 */
public class ChatterBoxMarkError extends ChatterBoxError {
    /**
     * Initialisation of error for the Mark command.
     */
    public ChatterBoxMarkError() {
        super("""
              ____________________________________________________________
              mark/ unmark Usage: mark/ unmark {Integer taskNo}
              ____________________________________________________________""");
    }
}

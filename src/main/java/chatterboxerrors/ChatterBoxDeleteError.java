package chatterboxerrors;

/**
 * Represents an error for the Delete command.
 */
public class ChatterBoxDeleteError extends ChatterBoxError {
    /**
     * Initialisation of error for the Delete command.
     */
    public ChatterBoxDeleteError() {
        super("""
              ____________________________________________________________
              delete Usage: delete {Integer taskNo}
              ____________________________________________________________""");
    }
}

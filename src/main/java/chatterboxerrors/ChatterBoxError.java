package chatterboxerrors;

/**
 * Represents a generic ChatterBox error.
 */
public class ChatterBoxError extends Exception {
    /**
     * Initialisation of a generic Chatterbox error.
     */
    public ChatterBoxError() {
        super("""
              ____________________________________________________________
              ChatterBox does not understand you :(
              ____________________________________________________________""");
    }

    /**
     * Initialisation of a generic Chatterbox error with a specific message.
     * @param message Any specific error message.
     */
    public ChatterBoxError(String message) {
        super(message);
    }
}

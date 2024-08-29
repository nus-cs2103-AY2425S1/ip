package ChatterBoxErrors;

/**
 * Represents a generic ChatterBox error.
 */
public class ChatterBoxError extends Exception {
    /**
     * Initialisation of a generic Chatterbox error.
     */
    public ChatterBoxError() {
        super("____________________________________________________________\n"
                + "ChatterBox does not understand you :(\n"
                + "____________________________________________________________");
    }

    /**
     * Initialisation of a generic Chatterbox error with a specific message.
     * @param message Any specific error message.
     */
    public ChatterBoxError(String message) {
        super(message);
    }
}

/**
 * DeltaException handles all exceptions specific to Delta Chatbot and
 * includes an error message to inform user of what is the error and how to fix it.
 */
public class DeltaException extends Exception {
    /**
     * Constructor for DeltaException instance.
     *
     * @param message Error message for instance.
     */
    public DeltaException(String message) {
        super(message);
    }
}

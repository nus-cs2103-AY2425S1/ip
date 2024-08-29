package sora;

/**
 * Custom Exception for Sora.
 */
public class SoraException extends Exception {
    /**
     * Constructs a new SoraException.
     *
     * @param message Message to communicate to user.
     */
    public SoraException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Invalid Command. Please Try Again. Reason: " + getMessage();
    }
}

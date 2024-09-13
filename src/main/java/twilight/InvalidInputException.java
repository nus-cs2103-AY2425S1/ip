package twilight;

/**
 * Handles all invalid inputs by user.
 */
public class InvalidInputException extends Exception {
    private final String message;

    /**
     * Instantiates an invalid input exception.
     *
     * @param message Error message.
     */
    public InvalidInputException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}

package greetbot;

/**
 * A exception class that is thrown when the input is not matching any keyword.
 */
public class RandomInputException extends Exception {

    private final String MESSAGE;

    /**
     * A constructor which constructs the exception object.
     * @param message The error message for the exception.
     */
    public RandomInputException(String message) {
        super();
        this.MESSAGE = message;
    }

    public String getMessage() {
        return this.MESSAGE;
    }
}

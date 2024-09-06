package greetbot;

/**
 * A exception class that is thrown when the input is not matching any keyword.
 */
public class RandomInputException extends Exception {

    private final String MESSAGE;

    public RandomInputException(String message) {
        super();
        this.MESSAGE = message;
    }

    public String getMessage() {
        return this.MESSAGE;
    }
}

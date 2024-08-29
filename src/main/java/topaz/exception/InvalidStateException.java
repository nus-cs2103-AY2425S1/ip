package topaz.exception;

/**
 * Represents an exception that is thrown when a Storage object fails to identify the state
 * of a task in txt file due to invalid number that represents the state.
 */
public class InvalidStateException extends Exception {
    private String state;

    /**
     * The invalid state input that caused the exception.
     * @param state
     */
    public InvalidStateException(String state) {
        super();
        this.state = state;
    }

    @Override
    public String toString() {
        return "State: " + state + " is invalid. Should be 1 or 0.";
    }
}

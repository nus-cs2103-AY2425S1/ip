package papadom.Exceptions;
/**
 * Exception thrown when the task number is missing or not provided by the user.
 */
public class NoTaskNumberException extends Exception {
    /**
     * Constructs a NoTaskNumberException with a message indicating that
     * the user must specify a task number.
     */
    public NoTaskNumberException() {
        super("Hmm... I don't see any task number... Please enter the number of the task!");
    }
}
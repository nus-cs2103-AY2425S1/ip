package edith.exception;

/**
 * This class extends Exception class. It is thrown when the user inputs a task number that is less than 0 or
 * more than stipulated number of tasks in the list.
 */
public class InvalidTaskNumberException extends Exception {
    public InvalidTaskNumberException() {
        super("oops! that's an invalid task number. please try again.");
    }
}

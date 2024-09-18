package gopher.exception;

/**
 * Thrown if a command that requires a task number is not given a task number
 */
public class MissingTaskNumberException extends Exception {
    @Override
    public String getMessage() {
        return "Sorry, I don't know which task you want to perform the operation on...\n"
                + "Please try again...";
    }
}

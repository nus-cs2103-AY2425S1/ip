package edith.exception;

/**
 * This class extends the Exception class. It is thrown when the user does not specify the task number.
 */
public class MissingTaskNumberException extends Exception {
    public MissingTaskNumberException() {
        super("which task would you like to mark/unmark/delete? Please specify with the task number. "
                + "for example:\n\n"
                + "      mark 3");
    }
}

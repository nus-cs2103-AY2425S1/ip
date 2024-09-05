package R2D2;

/**
 * Exception created to handle invalid inputs and to handle any errors in computation
 * of the tasks to add/delete/mark/unmark etc.
 */
public class BuzzException extends Exception {
    public BuzzException(String message) {
        super(message);
    }
 }

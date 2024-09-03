package ip.derrick;

/**
 * Exception class to handle items not present in the TaskList.
 */
public class MissingItemException extends Exception {
    public MissingItemException(String message) {
        super(message);
    }
}

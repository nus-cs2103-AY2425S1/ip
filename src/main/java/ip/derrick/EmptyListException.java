package ip.derrick;

/**
 * Exception class to handle an empty TaskList
 */
public class EmptyListException extends Exception {
    public EmptyListException(String message) {
        super(message);
    }
}

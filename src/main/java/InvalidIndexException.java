/**
 * This exception class indicates that the index keyed in by the user 
 * is out of bounds based on the task list.
 */
public class InvalidIndexException extends Exception{

    /**
     * Creates an InvalidIndexException exception object.
     * @param message The index keyed in by the user.
     */
    InvalidIndexException (int index) {
        
        super("I cannot access the task at index " + (index + 1) + " is out of bounds!!");
    }
}

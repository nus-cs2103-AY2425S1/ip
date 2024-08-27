/**
 * This exception class indicates that the index keyed in by the user 
 * is out of bounds based on the task list or is invalid.
 */
public class InvalidIndexException extends Exception{

    /**
     * Creates an InvalidIndexException exception object.
     * @param message The index keyed in by the user.
     */
    InvalidIndexException (String index) {
        
        super("The given index: " + index + ", is invalid or is out of bounds!!");
    }
}

/**
* This exception class indicates that the index keyed in by the user 
* is out of bounds based on the task list.
*/
public class InvalidIndexException extends Exception{

    /**
     * Constructor to create the InvalidIndexException exception.
     * @param message The error message to be displayed to the user
     */
    InvalidIndexException (int message) {
        
        super("I cannot access the task at index " + (message + 1) + " is out of bounds!!");
    }
}

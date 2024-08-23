/**
 * This is the DukeException
 * for when the user types incorrect inputs
 * such as forgetting the description of the task
 * or forgetting to put the /by
 */

public class DukeException extends Exception {
    
    public DukeException(String message) {
        super(message);
    }
    
}
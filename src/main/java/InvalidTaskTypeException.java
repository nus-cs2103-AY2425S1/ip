/**
* This exception class indicates that the task type keyed in by the user is not supported by Quack.
*/
public class InvalidTaskTypeException extends Exception{

    /**
     * Constructor to create the InvalidTaskException exception.
     * @param message The error message to be displayed to the user
     */
    InvalidTaskTypeException (String message) {

        super("Im sorry the task type " + message + " does not exist. These are the available tasks types : " + 
              "\n 1. ToDo \n 2. Deadline \n 3. Event");
    }
}

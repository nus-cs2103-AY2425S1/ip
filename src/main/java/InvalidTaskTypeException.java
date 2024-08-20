public class InvalidTaskTypeException extends Exception{

    InvalidTaskTypeException (String message) {
        super("Im sorry the task type " + message + " does not exist. These are the available tasks types : \n 1. ToDo \n 2. Deadline \n 3. Event");
    }
}

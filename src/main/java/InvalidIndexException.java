public class InvalidIndexException extends Exception{
    
    InvalidIndexException (int message) {
        super("I cannot access the task at index " + (message + 1) + " is out of bounds!!");
    }
}

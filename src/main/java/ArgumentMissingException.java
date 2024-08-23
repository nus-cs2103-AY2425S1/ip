/**
 * This class represent an exception which is thrown when the argument(s) of a task is missing
 * i.e. Deadline task required a deadline, Event task required a start and end date
 * @author Gan Ren Yick (A0276246X)
 */

public class ArgumentMissingException extends Exception{
    public ArgumentMissingException(String message) {
        super(message);
    }
}

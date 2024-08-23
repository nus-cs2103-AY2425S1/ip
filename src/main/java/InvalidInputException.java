/**
 * This class represent an exception which is thrown when the description of a task is missing
 * @author Gan Ren Yick (A0276246X)
 */

public class InvalidInputException extends Exception{
    public InvalidInputException(String message) {
        super(message);
    }
}

package fridayproject;

/*
 * Represents a FridayException.
 * A FridayException has a message.
 * Example: throw new FridayException("OOPS!!! The description of the task cannot be empty.");
 */
public class FridayException extends Exception {

    /*
     * Constructor for a FridayException.
     * @param message The message of the exception.
     */
    public FridayException(String message) {
        super(message);
    }
}
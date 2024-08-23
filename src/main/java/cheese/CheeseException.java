package cheese;

/**
 * Custom exception
 */
public class CheeseException extends Exception{
    /**
     * Just a constructor
     * @param message error message to inform user
     */
    public CheeseException(String message) {
        super(message);
    }
}

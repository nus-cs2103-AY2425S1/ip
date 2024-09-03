package pixy;

/**
 * Represents the exception class of Pixy.
 */
public class PixyExceptions extends Exception {

    /**
     * Prints the error message on encountering Pixy exceptions.
     * @param message The message to be displayed.
     */
    public PixyExceptions(String message) {
        super(message);
    }
}

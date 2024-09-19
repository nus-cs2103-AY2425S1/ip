package exceptions;

/**
 * TheOrangeRatchetCatException class is a custom exception class that is thrown when the user enters an invalid
 * command.
 */
public class TheOrangeRatchetCatException extends Exception {
    /**
     * TheOrangeRatchetCatException class constructor that takes in a message
     * @param message the message to be displayed when the exception is thrown
     */
    public TheOrangeRatchetCatException(String message) {
        super(message);
    }
}

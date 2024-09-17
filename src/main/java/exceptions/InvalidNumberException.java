package exceptions;

/**
 * Represents a class for an Exception where invalid number command is given.
 */
public class InvalidNumberException extends Exception {

    /**
     * Represents a constructor for an InvalidNumberException.
     */
    public InvalidNumberException() {
        super("Let's go input a proper number\nLet's go type help for valid commands");
    }
}

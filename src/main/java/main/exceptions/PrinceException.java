package main.exceptions;

/**
 * PrinceException is a custom exception.
 */
public class PrinceException extends Exception {

    /**
     * A constructor for PrinceException.
     * @param msg String representing the error message.
     */
    public PrinceException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}

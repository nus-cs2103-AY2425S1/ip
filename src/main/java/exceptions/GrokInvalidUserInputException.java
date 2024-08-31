package exceptions;

/**
 * Denotes a non-compliance to the specifications of the command.
 * The error message stored provides additional information about which part of the command was invalid.
 */
public class GrokInvalidUserInputException extends Exception {
    public GrokInvalidUserInputException() {
        super("Invalid user input");
    }

    public GrokInvalidUserInputException(String msg) {
        super(msg);
    }
}

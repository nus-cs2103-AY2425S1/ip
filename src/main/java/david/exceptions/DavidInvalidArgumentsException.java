package david.exceptions;

/**
 * Invalid argument exception class for David class
 */
public class DavidInvalidArgumentsException extends DavidException {
    public DavidInvalidArgumentsException() {}


    @Override
    public String toString() {
        return "Please enter valid arguments.";
    }
}

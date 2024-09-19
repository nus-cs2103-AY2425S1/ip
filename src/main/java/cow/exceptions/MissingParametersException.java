package cow.exceptions;

/** A custom exception that is thrown when there is a missing parameter. **/
public class MissingParametersException extends Exception {
    public MissingParametersException(String command, String expected) {
        super(command + " has missing parameters! Example: " + expected);
    }
}

package exceptions;

public class MissingParametersException extends Exception {
    public MissingParametersException(String command, String expected) {
        super(command + " has missing parameters! Example: " + expected);
    }
}

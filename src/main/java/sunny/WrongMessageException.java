package sunny;

/**
 * Inherits from exception, thrown when user input a invalid message or invalid timeline
 */
public class WrongMessageException extends Exception {
    public WrongMessageException() {
        super("Please enter your task in a proper format");
    }

    @Override
    public String toString() {
        return "invalid command:(( \n";
    }
}

package bobby.exceptions;

public class InvalidCommandFormatException extends BobbyException {
    public InvalidCommandFormatException(String command, String input) {
        super(String.format("You must provide a %s to %s", input, command));
    }
}

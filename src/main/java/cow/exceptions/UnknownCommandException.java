package cow.exceptions;

/** A custom exception that is thrown when it is an unknown command. **/
public class UnknownCommandException extends Exception {
    public UnknownCommandException() {
        super("Command not found!");
    }
}

package momo;

public class InvalidCommandException extends MomoException {
    public InvalidCommandException() {
        super("Your command is INVALID. You did not properly specify the type of task (todo/deadline/event) or command (bye/list)");
    }
    public InvalidCommandException(String message) {
        super(message);
    }
}

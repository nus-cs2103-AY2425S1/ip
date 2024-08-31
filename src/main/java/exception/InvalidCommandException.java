package exception;

/**
 * The InvalidCommandException class is used for invalid commands
 */
public class InvalidCommandException extends LevelHundredException {
    public InvalidCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}

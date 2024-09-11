package lemon.exception;
/**
 * Exception class for invalid commands
 * Used for commands that lemon is unable to process
 * @author He Yiheng
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String msg) {
        super(msg);
    }
}

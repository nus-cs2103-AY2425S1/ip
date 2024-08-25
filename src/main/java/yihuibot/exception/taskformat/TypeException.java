package yihuibot.exception.taskformat;

/**
 * An Exception for reading invalid task type from data. Type can only
 * be "T", "D", or "E".
 *
 * @author Toh Yi Hui A0259080A
 */
public class TypeException extends IncorrectTaskFormatException {
    /**
     * Constructor for a new TypeException.
     */
    public TypeException() {
        super("Invalid task type found in data. Task type can only be 'T', 'D', or 'E'.");
    }
}

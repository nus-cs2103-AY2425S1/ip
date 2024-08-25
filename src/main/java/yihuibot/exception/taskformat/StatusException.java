package yihuibot.exception.taskformat;

/**
 * An Exception for invalid status type in data. Status can only be 1 or 0.
 *
 * @author Toh Yi Hui A0259080A
 */
public class StatusException extends IncorrectTaskFormatException {
    /**
     * Constructor for a new StatusException.
     */
    public StatusException() {
        super("Invalid status type in data. Status can only be 1 or 0.");
    }
}

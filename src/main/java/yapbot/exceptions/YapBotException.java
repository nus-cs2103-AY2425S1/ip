package yapbot.exceptions;

/**
 * Custom Checked Exception for YapBot exceptions.
 */
public class YapBotException extends Exception {

    /**
     * Returns a YapBotException instance.
     *
     * @param description Message that is seen by users when the exception occurs.
     */
    public YapBotException(String description) {
        super(description);
    }

}

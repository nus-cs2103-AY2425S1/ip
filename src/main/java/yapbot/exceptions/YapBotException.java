package yapbot.exceptions;

/**
 * Custom Checked Exception for YapBot exceptions.
 */
public class YapBotException extends Exception {
    private static final String PREFIX_LINE = "\n-------------------------------------------\n";
    private static final String POSTFIX_LINE = "\n-------------------------------------------\n";

    /**
     * Returns a YapBotException instance.
     *
     * @param description Message that is seen by users when the exception occurs.
     */
    public YapBotException(String description) {
        super(PREFIX_LINE + description + POSTFIX_LINE);
    }

}

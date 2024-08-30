package yappingbot.exceptions;

/**
 * Base exception class for this bot.
 * For Runtime exceptions
 */
public class YappingBotException extends RuntimeException {

    public YappingBotException() {
        super();
    }

    public YappingBotException(String errorMsg) {
        super(errorMsg);
    }

    /**
     * Get stored error message.
     *
     * @return String of stored error message.
     */
    public String getErrorMessage() {
        return super.getMessage();
    }
}

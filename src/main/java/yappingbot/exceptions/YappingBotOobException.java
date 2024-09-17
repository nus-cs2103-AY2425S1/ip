package yappingbot.exceptions;

/**
 * YappingBotException for indices that are out of bounds.
 */
public class YappingBotOobException extends YappingBotException {

    /**
     * Constructs Exception for indices that are out of bounds of any list.
     *
     * @param errorMessage String message to be printed for clarity.
     * @param i integer that is out of bounds (will be automatically incremented by 1 to show
     *          human-friendly ordinal).
     */
    public YappingBotOobException(String errorMessage, int i) {
        super(String.format(errorMessage,
                            i + 1));
    }
}

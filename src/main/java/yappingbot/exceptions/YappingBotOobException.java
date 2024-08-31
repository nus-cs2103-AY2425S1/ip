package yappingbot.exceptions;

import yappingbot.stringconstants.ReplyTextMessages;

/**
 * YappingBotException for indices that are out of bounds.
 */
public class YappingBotOobException extends YappingBotException {

    /**
     * Constructs Exception for indices that are out of bounds of the task list.
     *
     * @param i integer that is out of bounds (will be automatically incremented by 1 to show
     *          human-friendly ordinal).
     */
    public YappingBotOobException(int i) {
        super(String.format(ReplyTextMessages.SELECT_TASK_MISSING_TEXT_1d,
                            i + 1));
    }
}

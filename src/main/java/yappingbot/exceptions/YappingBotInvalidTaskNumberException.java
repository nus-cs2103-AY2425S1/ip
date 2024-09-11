package yappingbot.exceptions;

import yappingbot.stringconstants.ReplyTextMessages;

/**
 * YappingBotException for any String failing to parse to an index.
 */
public class YappingBotInvalidTaskNumberException extends YappingBotException {
    /**
     * Constructs Exception when a String, expected to be an index, is unable to be parsed.
     *
     * @param userInputSlices The offending text that cannot be resolved into an integer.
     */

    public YappingBotInvalidTaskNumberException(String userInputSlices) {
        super(String.format(ReplyTextMessages.SELECT_TASK_NOT_INT_TEXT_1s, userInputSlices));
    }

}

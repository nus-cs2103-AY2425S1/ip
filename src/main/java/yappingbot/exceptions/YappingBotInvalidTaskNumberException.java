package yappingbot.exceptions;
import yappingbot.stringconstants.ReplyTextMessages;

public class YappingBotInvalidTaskNumberException extends YappingBotException {
    public YappingBotInvalidTaskNumberException(String userInputSlice) {
        super(String.format(ReplyTextMessages.SELECT_TASK_NOT_INT_TEXT_1s, userInputSlice));
    }
}

package yappingbot.exceptions;

import yappingbot.stringconstants.ReplyTextMessages;

public class YappingBotOobException extends YappingBotException {

    public YappingBotOobException(int i) {
        super(String.format(ReplyTextMessages.SELECT_TASK_MISSING_TEXT_1d,
                            i + 1));
    }
}

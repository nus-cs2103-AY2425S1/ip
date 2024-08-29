package yappingbot.exceptions;

import static yappingbot.stringconstants.ReplyTextMessages.INVALID_SAVE_FILE_EXCEPTION_1s;

public class YappingBotInvalidSaveFileException extends YappingBotException {

    public YappingBotInvalidSaveFileException(String issue) {
        super(String.format(INVALID_SAVE_FILE_EXCEPTION_1s, issue));
    }
}

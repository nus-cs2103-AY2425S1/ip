package yappingbot.exceptions;

import static yappingbot.stringconstants.ReplyTextMessages.SAVEFILE_IO_EXCEPTION_1s;

public class YappingBotSaveFileIoException extends YappingBotException {
    public YappingBotSaveFileIoException(String s) {
        super(String.format(SAVEFILE_IO_EXCEPTION_1s, s));
    }
}

package exceptions;

import java.io.IOException;

import static stringconstants.ReplyTextMessages.SAVEFILE_IO_EXCEPTION_1s;

public class YappingBotSaveFileIOException extends YappingBotException {
    public YappingBotSaveFileIOException(String s) {
        super(String.format(SAVEFILE_IO_EXCEPTION_1s,s));
    }
}

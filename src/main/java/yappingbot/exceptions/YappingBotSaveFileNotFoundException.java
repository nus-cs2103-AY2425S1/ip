package yappingbot.exceptions;
import yappingbot.stringconstants.ReplyTextMessages;
public class YappingBotSaveFileNotFoundException extends YappingBotException {
    public YappingBotSaveFileNotFoundException() {
        super(ReplyTextMessages.SAVE_FILE_NOT_FOUND_EXCEPTION);
    }
}

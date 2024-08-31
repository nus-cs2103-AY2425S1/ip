package yappingbot.exceptions;

import yappingbot.stringconstants.ReplyTextMessages;

/**
 * YappingBotException for when the savefile is not found.
 */


public class YappingBotSaveFileNotFoundException extends YappingBotException {
    /**
     * Constructs Exception when the savefile is unable to be loaded.
     */

    public YappingBotSaveFileNotFoundException() {
        super(ReplyTextMessages.SAVE_FILE_NOT_FOUND_EXCEPTION);
    }

}

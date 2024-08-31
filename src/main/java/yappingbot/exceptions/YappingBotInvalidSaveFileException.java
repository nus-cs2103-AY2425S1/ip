package yappingbot.exceptions;

import static yappingbot.stringconstants.ReplyTextMessages.INVALID_SAVE_FILE_EXCEPTION_1s;

/**
 * YappingBotException for errors trying to read the Savefile.
 */
public class YappingBotInvalidSaveFileException extends YappingBotException {

    /**
     * Constructs Exception for when there is an issue trying to load the savefile.
     *
     * @param issue Specific issue or message encountered trying to load savefile.
     */
    public YappingBotInvalidSaveFileException(String issue) {
        super(String.format(INVALID_SAVE_FILE_EXCEPTION_1s, issue));
    }
}

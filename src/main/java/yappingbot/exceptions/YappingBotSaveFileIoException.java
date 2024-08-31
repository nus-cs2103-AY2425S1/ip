package yappingbot.exceptions;

import static yappingbot.stringconstants.ReplyTextMessages.SAVEFILE_IO_EXCEPTION_1s;

/**
 * YappingBotException for issues trying to save file to disk.
 */
public class YappingBotSaveFileIoException extends YappingBotException {
    /**
     * Constructs Exception for when there is an issue trying to save the savefile.
     *
     * @param issue Specific issue or message encountered trying to save savefile.
     */
    public YappingBotSaveFileIoException(String issue) {
        super(String.format(SAVEFILE_IO_EXCEPTION_1s, issue));
    }
}

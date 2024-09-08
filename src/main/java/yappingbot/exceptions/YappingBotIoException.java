package yappingbot.exceptions;

import static yappingbot.stringconstants.ReplyTextMessages.IO_EXCEPTION_1s;

/**
 * YappingBotException for issues trying to deal with general Input/Output.
 */
public class YappingBotIoException extends YappingBotException {
    /**
     * Constructs Exception for when there is an issue trying to interact with IO.
     *
     * @param issue Specific issue or message encountered trying process IO.
     */
    public YappingBotIoException(String issue) {
        super(String.format(IO_EXCEPTION_1s, issue));
    }
}

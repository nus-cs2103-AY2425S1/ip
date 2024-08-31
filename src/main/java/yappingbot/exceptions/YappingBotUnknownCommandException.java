package yappingbot.exceptions;
import yappingbot.stringconstants.ReplyTextMessages;

/**
 * YappingBotException for unknown commands.
 */
public class YappingBotUnknownCommandException extends YappingBotException {
    /**
     * Constructs Exception to provide help text, if nothing was inputted.
     */
    public YappingBotUnknownCommandException() {
        super(ReplyTextMessages.HELP_TEXT);
    }


    /**
     * Constructs Exception for a command that is not implemented.
     * @param userInput String of text inputted by user that caused the error.
     */
    public YappingBotUnknownCommandException(String userInput) {
        super(String.format(ReplyTextMessages.UNKNOWN_COMMAND_TEXT_1s + ReplyTextMessages.HELP_TEXT, userInput));
    }
}

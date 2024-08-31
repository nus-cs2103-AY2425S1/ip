package yappingbot.exceptions;

import yappingbot.stringconstants.ReplyTextMessages;

public class YappingBotUnknownCommandException extends YappingBotException {

    public YappingBotUnknownCommandException() {
        super(ReplyTextMessages.HELP_TEXT);
    }

    public YappingBotUnknownCommandException(String userInput) {
        super(String.format(
                ReplyTextMessages.UNKNOWN_COMMAND_TEXT_1s + ReplyTextMessages.HELP_TEXT,
                userInput));
    }
}

package yappingbot.exceptions;

import yappingbot.stringconstants.ReplyTextMessages;

/**
 * YappingBotException for any incorrectly formatted commands.
 */
public class YappingBotIncorrectCommandException extends YappingBotException {
    /**
     * Constructs exception for commands that are malformed.
     *
     * @param usageMessage String of usage of command.
     * @param userInput String of what user typed in that resulted in error.
     */
    public YappingBotIncorrectCommandException(String usageMessage, String userInput) {
        super(String.format(ReplyTextMessages.UNKNOWN_COMMAND_TEXT_1s + usageMessage, userInput));
    }

    /**
     * Constructs exception for commands that are not found or malformed.
     *
     * @param usageMessage String of usage of command, if error is due to an incorrect use of
     *                     message.
     * @param userInput StringArray of what user typed in that resulted in error.
     */
    public static YappingBotIncorrectCommandException withUserInputArray(String usageMessage,
                                                                         String[] userInput) {
        assert usageMessage != null;
        assert userInput != null;
        StringBuilder sb = new StringBuilder();
        for (String s : userInput) {
            sb.append(s);
            sb.append(" ");
        }
        return new YappingBotIncorrectCommandException(usageMessage, sb.toString().trim());
    }
}

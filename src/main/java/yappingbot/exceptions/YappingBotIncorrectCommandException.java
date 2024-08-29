package yappingbot.exceptions;
import yappingbot.stringconstants.ReplyTextMessages;

public class YappingBotIncorrectCommandException extends YappingBotException {
    public YappingBotIncorrectCommandException (String UsageMessage, String userInput) {
        super(String.format(ReplyTextMessages.UNKNOWN_COMMAND_TEXT_1s + UsageMessage, userInput));
    }

    public static YappingBotIncorrectCommandException withUserInputArray(String UsageMessage, String[] userInput) {
        StringBuilder sb = new StringBuilder();
        for (String s: userInput) {
            sb.append(s);
            sb.append(" ");
        }
        return new YappingBotIncorrectCommandException(UsageMessage, sb.toString().trim());
    }
}

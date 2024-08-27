public class YappingBotIncorrectCommandException extends YappingBotException {
    public YappingBotIncorrectCommandException (String UsageMessage, String userInput) {
        super(String.format(ReplyTextMessages.UNKNOWN_COMMAND_TEXT_1s + UsageMessage, userInput));
    }
}

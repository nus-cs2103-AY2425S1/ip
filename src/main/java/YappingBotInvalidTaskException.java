public class YappingBotInvalidTaskException extends YappingBotException {
    public YappingBotInvalidTaskException(String userInputSlice) {
        super(String.format(ReplyTextMessages.SELECT_TASK_NOT_INT_TEXT_1s, userInputSlice));
    }
}

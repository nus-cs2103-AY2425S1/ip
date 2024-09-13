package bot.exceptions;

public class InvalidTaskEnumException extends BotException {
    public InvalidTaskEnumException(String e) {
        super("Invalid task enum: " + e);
    }
}

package bot.exceptions;

public class InvalidTaskDescriptionException extends BotException {
    public InvalidTaskDescriptionException(String msg) {
        super("Task description is invalid: " + msg);
    }
}

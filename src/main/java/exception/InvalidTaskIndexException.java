package exception;

public class InvalidTaskIndexException extends BotException {
    public InvalidTaskIndexException(String taskIndex) {
        super("Invalid task index: " + taskIndex + "!\nTask index must be a positive integer");
    }
}

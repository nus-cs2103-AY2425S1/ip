package bot.exceptions;

public class InvalidTaskIdException extends BotException {
    public InvalidTaskIdException(int id) {
        super(id + " is not a valid task ID");
    }
}

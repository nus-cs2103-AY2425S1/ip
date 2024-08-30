package bot.exceptions;

public class InvalidCommandException extends BotException {
    public InvalidCommandException(String cmd) {
        super("Invalid command: " + cmd);
    }
}

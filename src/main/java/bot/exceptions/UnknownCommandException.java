package bot.exceptions;

public class UnknownCommandException extends BotException {
    public UnknownCommandException(String cmd) {
        super("Unknown command: " + cmd);
    }
}

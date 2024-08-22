package bot.exceptions;

public class BotException extends Exception {
    public BotException(String msg) {
        super("Ooops... Something went wrong:\n\n" + msg + "\n\nType 'help' to see what you can do.");
    }
}

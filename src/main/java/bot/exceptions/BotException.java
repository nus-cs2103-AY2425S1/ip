package bot.exceptions;

public class BotException extends Exception {
    public BotException(String msg) {
        // TODO: Move "Type 'help' to see..." into error formatter
        super("Ooops... Something went wrong:\n\n" + msg + "\n\nType 'help' to see what you can do.");
    }
}

public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super("Let's go inputting valid commands only\nLet's go type /help for valid commands");
    }
}

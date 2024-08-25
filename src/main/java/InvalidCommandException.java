public class InvalidCommandException extends InputException {
    public InvalidCommandException() {
        super("Sorry, the command entered is not in my vocabulary!");
    }
}

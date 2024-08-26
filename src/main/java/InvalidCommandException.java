public class InvalidCommandException extends Exception{
    public InvalidCommandException(String msg) {
        super(msg + ": Is not a valid command.");
    }
}

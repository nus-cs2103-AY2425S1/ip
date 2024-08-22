public class UnknownCommandException extends Exception{
    public UnknownCommandException() {
        super("That command isn't recognized! Please try again with the current commands!");
    }
}

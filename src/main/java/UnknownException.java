public class UnknownException extends MittensException {
    private static final String MITTENS_MESSAGE = "Meow?! Something went wrong...";
    private static final String HELP_MESSAGE = "You can try again, or type 'help' to see a list of commands.";
    
    public UnknownException(String message) {
        super(message, MITTENS_MESSAGE, HELP_MESSAGE);
    }
}

public class BadInputException extends MittensException {
    private static final String MITTENS_MESSAGE = "Meow?! What does that mean?";
    private static final String HELP_MESSAGE = "Type 'help' to see a list of commands," +
            " or 'help <command>' to see more about a specific command.";
    
    public BadInputException(String message) {
        super(message, MITTENS_MESSAGE, HELP_MESSAGE);
    }
}

public class Parser {
    // Deals with making sense of the user command
    public static Command parse(String fullCommand) {
        return new ByeCommand();
    }
}

package ip.derrick;

/**
 * Parse the user input and returns the commands that the user is trying to specify.
 */
public class Parser {

    /**
     * Finds the enum value which is present in the user input.
     * @param input The user input.
     * @return An enum value from the Commands class.
     */
    public CommandHandler returnCommand(String input) {
        String instructions = input.split(" ")[0];
        return new CommandHandler(Commands.fromString(instructions));
    }

}

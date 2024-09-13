package bibi;

/**
 * Represents the object that converts the inputs received from user into workable information.
 */
public class Parser {
    /**
     * Constructs and returns a Command which can be executed by the program.
     *
     * @param input The words the user has input into the console.
     * @return The Command to execute.
     */
    public static Command parseCommand(String input) {
        String cmd = input.split(" ")[0];
        return new Command(cmd, input.substring(cmd.length()).trim());
    }
}

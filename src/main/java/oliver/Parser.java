package oliver;

/**
 * Deals with making sense of the user command
 */
public class Parser {
    /**
     * Parses the user input and returns the command word present.
     *
     * @param input the input provided by the user
     * @return command word in the user's input
     */
    public static String parseCommand(String input) {
        return input.split(" ")[0];
    }

    /**
     * Parses the user input and returns the arguments present.
     *
     * @param input the input provided by the user
     * @return arguments in the user's input
     */
    public static String parseArgs(String input) {
        return input.split(" ", 2)[1];
    }

}

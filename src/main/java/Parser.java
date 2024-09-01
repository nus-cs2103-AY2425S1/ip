/**
 * The Parser class deals with making sense of the user command.
 */
public class Parser {
    /**
     * Parses the task number from the user's command.
     *
     * @param userCommand The command entered by the user.
     * @return The parsed user command.
     */
    public static String parseCommand(String userCommand) {
        String[] userInputs = userCommand.split(" ");
        return userInputs[0];
    }

    /**
     * Parses the task number from the user's command.
     *
     * @param userCommand The command entered by the user.
     * @return The parsed task number.
     * @throws Exception If the task number is invalid.
     */
    public static int parseTaskNumber(String userCommand) throws Exception {
        try {
            String command = parseCommand(userCommand);
            return Integer.parseInt(userCommand.substring(command.length() + 1).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new Exception("Please enter a valid task number!");
        }
    }
}

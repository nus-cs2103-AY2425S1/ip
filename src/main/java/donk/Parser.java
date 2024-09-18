package donk;




/**
 * Contains methods that parse the user input
 */
public class Parser {

    /**
     * Checks if the given string is a valid integer.
     *
     * @param s The string to be checked.
     * @return true if the string can be parsed as an integer, otherwise false.
     */
    public static boolean validNum(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Parses the user's input command and executes the corresponding action.
     * @param fullCommand The full command string entered by the user.
     * @throws Exception If the command is not recognized or if there are issues processing the command.
     */
    public static String parse(String fullCommand) throws Exception {
        String[] inputArray = fullCommand.split("\\s+");
        String command = inputArray[0];
        if (command.isBlank()) {
            throw new Exception("That's empty mate");
        }

        return command;
    }


}

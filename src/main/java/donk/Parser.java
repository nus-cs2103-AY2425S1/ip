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

    /**
     * Parse input array and return index for mark or unmark commands
     * @param inputArray
     * @return int, index
     * @throws Exception
     */
    public static int parseIndex(String[] inputArray) throws Exception {
        if (inputArray.length < 2 || !inputArray[1].matches("\\d+")) {
            throw new Exception("Invalid input, needs an index");
        }
        int index = Integer.parseInt(inputArray[1]) - 1;
        return index;
    }

}

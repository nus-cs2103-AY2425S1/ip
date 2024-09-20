package casper.components;

import casper.exceptions.NoArgumentException;

/**
 * A utility class for parsing user input and checking arguments.
 */
public class Parser {

    /**
     * Splits the input string into a command and an argument.
     *
     * @param input The input string to be split.
     * @return An array of strings where the first element is the command and the second element is the argument.
     */
    public static String[] parseInputToCommandAndArgument(String input) {
        return input.split(" ", 2);
    }

    /**
     * Splits the argument string based on the specified keyword.
     *
     * @param argument The argument string to be split.
     * @param keyword The keyword to split the argument by.
     * @return An array of strings where the first element is the part before the keyword and the second element is the
     *         part after the keyword.
     */
    public static String[] parseArgumentByKeyword(String argument, String keyword) {
        return argument.split(String.format(" /%s ", keyword), 2);
    }

    /**
     * Parses a sort command's argument into two parts: the first three characters and the rest of the string.
     * If the argument is shorter than or equal to three characters, the second part will be empty.
     *
     * @param argument The string argument to be parsed.
     * @return A string array containing two elements: the first three characters (or the entire argument if it is
     *         shorter than three characters), and the remainder of the argument (or an empty string if none).
     */
    public static String[] parseSortArgument(String argument) {
        String firstPart = argument.length() > 3 ? argument.substring(0, 3) : argument;
        String secondPart = argument.length() > 3 ? argument.substring(3).trim() : "";
        return new String[]{firstPart, secondPart};
    }

    /**
     * Checks if the argument is null and throws an exception if it is.
     *
     * @param argument The argument to check.
     * @param command The command associated with the argument.
     * @throws NoArgumentException If the argument is null.
     */
    public static void argumentCheck(String argument, String command) throws NoArgumentException {
        if (argument == null) {
            throw new NoArgumentException(command);
        }
    }
}

package alfred.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import alfred.ui.AlfredResponse;

/**
 * Provides methods to parse user input commands and extract relevant information.
 * This class is responsible for interpreting user input and validating commands.
 */
public class Parser {

    /**
     * Extracts the task number from a given user input string.
     * Assumes that the task number is the second word in the input string.
     *
     * @param input The user input string containing the command and task number.
     * @return The task number as an integer.
     * @throws NumberFormatException If the task number is not a valid integer.
     */
    public static int getTaskNumberFromInput(String input) {
        String[] parts = input.split(" ");
        return Integer.parseInt(parts[1]);
    }

    /**
     * Extracts the command keyword from the user input string.
     * Assumes that the command is the first word in the input string.
     *
     * @param input The user input string containing the command.
     * @return The command keyword as a string.
     */
    public static String getCommand(String input) {
        return input.split(" ")[0];
    }

    /**
     * Validates whether the user input matches a given command action and task number format.
     * Ensures that the command follows the correct format and the task number is within bounds.
     *
     * @param input The user input string containing the command and task number.
     * @param action The expected action keyword to match in the input string.
     * @param listSize The current size of the task list to validate the task number.
     * @return A string containing the error message if validation fails, or an empty string if valid.
     */
    public static String validateCommand(String input, String action, int listSize) {
        String regex = "^" + action + " \\d+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // Check if the input matches the expected command pattern
        if (!matcher.matches()) {
            return AlfredResponse.showInvalidCommandFormat();
        }

        // Extract the task number and validate it is within the bounds of the task list
        int taskNumber = Parser.getTaskNumberFromInput(input);
        if (taskNumber <= 0 || taskNumber > listSize) {
            return AlfredResponse.showInvalidTaskNumber(listSize);
        }

        // Return an empty string indicating everything is valid
        return "";
    }

    /**
     * Extracts and returns the keyword from the user's find command input.
     *
     * @param input The full user input string.
     * @return The keyword to search for.
     */
    public static String getKeyword(String input) {
        return input.substring(input.indexOf(" ") + 1).trim();
    }
}

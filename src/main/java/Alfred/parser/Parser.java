package alfred.parser;

import alfred.ui.Ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * @return true if the input is a valid command and the task number is within bounds; false otherwise.
     */
    public static boolean isValidCommand(String input, String action, int listSize) {
        String regex = "^" + action + " \\d+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            Ui.showInvalidCommandFormat();
            return false;
        }

        int taskNumber = Parser.getTaskNumberFromInput(input);
        if (taskNumber <= 0 || taskNumber > listSize) {
            Ui.showInvalidTaskNumber(listSize);
            return false;
        }
        return true;
    }
}

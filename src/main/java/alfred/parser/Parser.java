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
        String[] parts = input.split(" ");
        return parts[0];
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
        boolean isTagRelated = isTagCommand(action);
        if (isTagRelated && !isValidCommandFormat(input, action)) {
            return AlfredResponse.showInvalidTagFormat(action);
        }

        if (!isValidCommandFormat(input, action)) {
            return AlfredResponse.showInvalidCommandFormat();
        }

        // Extract the task number and validate it is within the bounds of the task list
        int taskNumber = getTaskNumberFromInput(input);
        if (!isTaskNumberValid(listSize, taskNumber)) {
            return AlfredResponse.showInvalidTaskNumber(listSize);
        }

        // Return an empty string indicating everything is valid
        return "";
    }

    private static boolean isTagCommand(String action) {
        return action.equals("tag") || action.equals("untag");
    }

    /**
     * Validates whether the input string matches the command format.
     *
     * @param input The user input string.
     * @param action The action to match.
     * @return True if the input matches the expected command format, false otherwise.
     */
    private static boolean isValidCommandFormat(String input, String action) {
        String regex = getCommandFormat(action);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    /**
     * Returns the regular expression that corresponds to the action command.
     *
     * @param action The action for which the command format is retrieved.
     * @return The regular expression string corresponding to the action.
     */
    private static String getCommandFormat(String action) {
        switch (action) {
        case "tag":
            return "^tag\\s+\\d+\\s+(.+?)$";
        case "untag":
            return "^untag\\s+\\d+\\s+(.+?)$";
        default:
            return "^" + action + " \\d+$";
        }
    }

    /**
     * Checks whether the given task number is valid within the task list bounds.
     *
     * @param taskNumber The task number to validate.
     * @param listSize The size of the current task list.
     * @return True if the task number is valid, false otherwise.
     */
    private static boolean isTaskNumberValid(int listSize, int taskNumber) {
        return taskNumber > 0 && taskNumber <= listSize;
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

    /**
     * Extracts the tag from a tag command.
     * The input must follow the format: tag taskNumber tagString
     *
     * @param input The user input string in the form tag taskNumber tagString
     * @return The extracted tag as a string.
     * @throws IllegalArgumentException If the input format is invalid.
     */
    public static String getTagFromInput(String input) {
        // Split the input based on spaces
        String[] inputParts = input.split("\\s+");

        // Validate that the input has at least 3 parts ("tag", task number, and the tag)
        if (inputParts.length < 3) {
            throw new IllegalArgumentException("Invalid input format. Expected format: 'tag <taskNumber> <tag>'.");
        }

        // The tag is the third part of the input (index 2)
        return inputParts[2];
    }
}

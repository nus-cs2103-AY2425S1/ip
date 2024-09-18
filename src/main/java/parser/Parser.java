package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddTodoCommand;
import command.BreakCommand;
import command.Command;
import command.DeleteTaskCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkTaskCommand;
import command.UnmarkTaskCommand;
import command.UpdateTaskCommand;
import exceptions.DelphiException;
import exceptions.EmptyInputException;
import exceptions.InvalidInputException;
import exceptions.InvalidListItemException;

/**
 * Provides the parsing functionality that allows ui.Delphi to process different types of input.
 * It identifies commands and creates corresponding Command objects or throws exceptions for invalid inputs.
 *
 * @author jordanchan
 */
public class Parser {

    private final DateParser d = new DateParser();

    /**
     * Parses the given input string and returns an appropriate Command object based on the content of the input.
     * Recognizes specific commands and creates instances of their corresponding Command subclasses.
     *
     * @param input The input string to be parsed.
     * @return A Command object representing the parsed input.
     * @throws DelphiException If the input does not match any known command.
     */
    public Command parseInput(String input) throws DelphiException {
        if (input.equals("bye")) {
            return new BreakCommand();
        } else if (checkStringPrefix(input, 4, "mark")) {
            return new MarkTaskCommand(extractIntegerAsString(input));
        } else if (checkStringPrefix(input, 6, "unmark")) {
            return new UnmarkTaskCommand(extractIntegerAsString(input));
        } else if (checkStringPrefix(input, 6, "delete")) {
            return new DeleteTaskCommand(extractIntegerAsString(input));
        } else if (input.trim().equals("list")) {
            return new ListCommand(input);
        } else if (checkStringPrefix(input, 4, "todo")) {
            return new AddTodoCommand(input);
        } else if (checkStringPrefix(input, 8, "deadline")) {
            return new AddDeadlineCommand(input);
        } else if (checkStringPrefix(input, 5, "event")) {
            return new AddEventCommand(input);
        } else if (checkStringPrefix(input, 4, "find")) {
            return new FindCommand(input);
        } else if (checkStringPrefix(input, 6, "update")) {
            return new UpdateTaskCommand(input);
        } else if (input.trim().isEmpty()) {
            throw new EmptyInputException();
        } else {
            throw new InvalidInputException();
        }
    }

    /**
     * Checks if the first part of the input matches a certain string up to a given number of characters.
     *
     * @param original The string to be compared against.
     * @param index The number of characters to compare from the start of the string.
     * @param comparison The string to compare the prefix against.
     * @return True if the prefix of the original string matches the comparison string, false otherwise.
     */
    public boolean checkStringPrefix(String original, int index, String comparison) {
        // Ensure the index is within the bounds of the original string
        if (index > original.length()) {
            index = original.length();
        }

        // Get the substring from the original string up to the specified index
        String prefix = original.substring(0, index);

        // Compare the prefix with the comparison string
        return prefix.equals(comparison);
    }

    /**
     * Formats a deadline string by extracting and reformatting the components.
     *
     * @param input The input string containing the deadline information.
     * @return The formatted string where the deadline information is represented as "/by date".
     */
    public static String formatStringDeadline(String input) {
        // Extract the parts using regex
        String regex = "(.*) \\(by: (.*)\\)";
        return input.replaceAll(regex, "$1 /by $2");
    }

    /**
     * Formats an event string by extracting and reformatting the components.
     *
     * @param input The input string containing the event information.
     * @return The formatted string where the event information is represented as "/from  start /to end".
     */
    public static String formatStringEvent(String input) {
        //chatgpt was used to help write this
        // Extract the parts using regex
        String regex = "(.*) \\(from: (.*) to: (.*)\\)";
        return input.replaceAll(regex, "$1 /from $2 /to $3");
    }

    /**
     * Parses a deadline string and extracts the task description and the formatted deadline.
     * The input string is expected to contain the task description followed by "/by" and then the deadline.
     *
     * @param s The input string containing the task description and deadline, separated by "/by".
     * @return A String array where:
     *         - The first element is the task description (trimmed).
     *         - The second element is the formatted deadline wrapped in "(by: ...)".
     * @throws InvalidInputException if the input string does not contain "/by".
     */
    public String[] parseDeadline(String s) throws InvalidInputException {
        //chatgpt was used to help write this
        String[] res = new String[2];
        int slashIndex = s.indexOf("/by");
        if (slashIndex == -1) {
            throw new InvalidInputException();
        }
        //remove whitespace to ensure correct formatting
        res[0] = s.substring(0, slashIndex).trim();

        // Extract the substring after the slash and trim it
        String deadline = s.substring(slashIndex + 3).trim();
        if (deadline.isEmpty()) {
            throw new InvalidInputException("Please input a deadline.");
        }
        String date = d.parseAndFormatDateTime(deadline);
        if (date != null) {
            deadline = "(by: " + date + ")";
        } else {
            deadline = "(by: " + s.substring(3).trim() + ")";
        }
        res[1] = deadline;
        return res;
    }

    /**
     * Parses an event string into its description, start time, and end time.
     * The event string must contain "/from" to indicate the start time and "/to" to indicate the end time.
     * If the format is invalid, it throws an InvalidInputException.
     *
     * @param s The event string to be parsed, which must contain the description, "/from", and "/to" parts.
     * @return A string array where:
     *         - res[0] is the event description,
     *         - res[1] is the formatted start time prefixed with "from: ",
     *         - res[2] is the formatted end time prefixed with "to: ".
     * @throws InvalidInputException If the input string does not contain
     *     valid "/from" and "/to" parts or if "to" comes before "from".
     */
    public String[] parseEvent(String s) throws InvalidInputException {
        //chatgpt was used to help write this
        String[] res = new String[3];

        int fromIndex = s.indexOf("/from");
        int toIndex = s.indexOf("/to");

        // make sure there is a "from" and "to" part
        if (fromIndex == -1 || toIndex == -1 || toIndex <= fromIndex) {
            throw new InvalidInputException();
        }

        // Extract the event description
        res[0] = s.substring(0, fromIndex).trim();

        // Extract the start and end time
        String fromPart = s.substring(fromIndex + "/from".length(), toIndex).trim();
        String toPart = s.substring(toIndex + "/to".length()).trim();

        if (fromPart.isEmpty() || toPart.isEmpty()) {
            throw new InvalidInputException("Please input both start and end times.");
        }

        // Reformat the output
        String formattedFromPart = d.parseAndFormatDateTime(fromPart);
        if (formattedFromPart != null) {
            fromPart = "from: " + formattedFromPart;
        } else {
            fromPart = "from: " + fromPart;
        }
        res[1] = fromPart;

        String formattedToPart = d.parseAndFormatDateTime(toPart);
        if (formattedFromPart != null) {
            toPart = "to: " + formattedToPart;
        } else {
            toPart = "to: " + toPart;
        }
        res[2] = toPart;
        return res;
    }

    public String extractIntegerAsString(String input) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group();  // Return the string representation of the integer
        } else {
            return "-1";
        }
        //throw new NumberFormatException("No integer found in the input string.");
    }
}

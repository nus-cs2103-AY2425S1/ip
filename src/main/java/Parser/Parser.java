package Parser;

import Commands.AddDeadlineCommand;
import Commands.AddEventCommand;
import Commands.AddTodoCommand;
import Commands.BreakCommand;
import Commands.Command;
import Commands.DeleteTaskCommand;
import Commands.FindCommand;
import Commands.ListCommand;
import Commands.MarkTaskCommand;
import Commands.UnmarkTaskCommand;
import Exceptions.DelphiException;
import Exceptions.InvalidInputException;

/**
 * Provides the parsing functionality that allows UI.Delphi to process different types of input.
 * It identifies commands and creates corresponding Command objects or throws exceptions for invalid inputs.
 *
 * @author jordanchan
 */
public class Parser {

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
            return new MarkTaskCommand(input);
        } else if (checkStringPrefix(input, 6, "unmark")) {
            return new UnmarkTaskCommand(input);
        } else if (checkStringPrefix(input, 6, "delete")) {
            return new DeleteTaskCommand(input);
        } else if (input.equals("list")) {
            return new ListCommand(input);
        } else if (checkStringPrefix(input, 4, "todo")) {
            return new AddTodoCommand(input);
        } else if (checkStringPrefix(input, 8, "deadline")) {
            return new AddDeadlineCommand(input);
        } else if (checkStringPrefix(input, 5, "event")) {
            return new AddEventCommand(input);
        } else if (checkStringPrefix(input, 4, "find")) {
            return new FindCommand(input);
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
        // Extract the parts using regex
        String regex = "(.*) \\(from: (.*) to: (.*)\\)";
        return input.replaceAll(regex, "$1 /from $2 /to $3");
    }
}

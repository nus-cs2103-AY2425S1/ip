package wiggly.parser;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wiggly.commands.AddCommand;
import wiggly.commands.Command;
import wiggly.commands.DeleteCommand;
import wiggly.commands.ExitCommand;
import wiggly.commands.FindCommand;
import wiggly.commands.InvalidCommand;
import wiggly.commands.ListCommand;
import wiggly.commands.MarkCommand;
import wiggly.exception.WigglyException;
import wiggly.task.TaskType;

/**
 * A class representation of the Parser
 */
public class Parser {


    // This is the standard user input parsing pattern which looks like the following:
    // <command> <description> <by date if there is a preceeding /by> <from date if there is a preceeding /from>
    // <to date if there is a preceeding /to>
    public static final Pattern USER_INPUT_PATTERN = Pattern.compile(
        "(?<command>\\S+)"
            + "(?:\\s+(?<description>[^/]*))?"
            + "(?:\\s*/by\\s+(?<by>[^/]*))?"
            + "(?:\\s*/from\\s+(?<from>[^/]*))?"
            + "(?:\\s*/to\\s+(?<to>[^/]*))?"
    );


    public Parser() {

    }

    /**
     * Parses the given String into commands, description, by, from, to dates
     * @param input The string to be parsed
     * @return The corresponding Command instance based on the parsed input
     * @throws WigglyException If the String input cannot be parsed correctly
     */
    public static Command parse(String input) throws WigglyException {
        Matcher matcher = USER_INPUT_PATTERN.matcher(input);
        // Find and print commands, descriptions, and parameters
        if (!matcher.find()) {
            return new InvalidCommand();
        }

        try {
            String command = matcher.group("command").trim();
            String description = matcher.group("description");
            String by = matcher.group("by");
            String from = matcher.group("from");
            String to = matcher.group("to");

            switch (command) {
            case "list":
                return new ListCommand();
            case "todo":
                return new AddCommand(TaskType.TODO, description.trim(), null, null, null);
            case "deadline":
                return new AddCommand(TaskType.DEADLINE, description.trim(),
                    LocalDate.parse(by.trim()), null, null);
            case "event":
                return new AddCommand(TaskType.EVENT, description.trim(), null,
                    LocalDate.parse(from.trim()), LocalDate.parse(to.trim()));
            case "bye":
                return new ExitCommand();
            case "mark":
                return new MarkCommand(Integer.parseInt(description.trim()), true);
            case "unmark":
                return new MarkCommand(Integer.parseInt(description.trim()), false);
            case "delete":
                return new DeleteCommand(Integer.parseInt(description.trim()));
            case "find":
                return new FindCommand(description.trim());
            default:
                return new InvalidCommand();
            }
        } catch (NullPointerException e) {
            throw new WigglyException("Failed to parse input: " + input);
        }

    }
}

package topaz.main;

import topaz.command.Command;
import topaz.command.CreateCommand;
import topaz.command.DeleteCommand;
import topaz.command.FindCommand;
import topaz.command.MarkCommand;
import topaz.command.StatisticsCommand;
import topaz.command.TextCommand;
import topaz.exception.InvalidCommandException;

/**
 * Parses user input into the appropriate {@link Command} object.
 * This class interprets a given command prompt and returns the corresponding command
 * to be executed.
 */
public class Parser {
    /**
     * Parses a user input prompt and returns the corresponding {@link Command} object.
     *
     * <p>The method recognizes various commands such as "help", "bye", "list", "mark",
     * "unmark", "todo", "deadline", "event", and "delete". If the input does not match
     * any recognized commands, an {@link InvalidCommandException} is thrown.</p>
     *
     * @param prompt The user input command prompt to be parsed.
     * @return The corresponding {@link Command} object based on the input prompt.
     * @throws InvalidCommandException If the input prompt does not match any valid command.
     */
    public static Command parse(String prompt) throws InvalidCommandException {

        if (prompt.equalsIgnoreCase("help")) {
            return new TextCommand("help");
        } else if (prompt.contains("bye")) {
            return new TextCommand("bye");
        } else if (prompt.equals("list")) {
            return new TextCommand("list");
        } else if (prompt.startsWith("find")) {
            return new FindCommand(prompt);

        } else if (prompt.startsWith("mark")) {
            int index = Integer.parseInt(prompt.substring(5));
            return new MarkCommand("mark", index);
        } else if (prompt.startsWith("unmark")) {
            int index = Integer.parseInt(prompt.substring(7));
            return new MarkCommand("unmark", index);

        } else if (prompt.startsWith("todo")) {
            return new CreateCommand("todo", prompt);
        } else if (prompt.startsWith("deadline")) {
            return new CreateCommand("deadline", prompt);
        } else if (prompt.startsWith("event")) {
            return new CreateCommand("event", prompt);

        } else if (prompt.startsWith("delete")) {
            int index = Integer.parseInt(prompt.substring(7));
            return new DeleteCommand("delete", index);
        } else if (prompt.startsWith("statistic")) {
            return new StatisticsCommand();

        } else {
            throw new InvalidCommandException(prompt);
        }
    }
}

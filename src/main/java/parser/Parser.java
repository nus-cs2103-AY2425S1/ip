package parser;

import java.time.format.DateTimeParseException;

import command.AddCommand;
import command.ArchiveCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.InvalidCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import task.Deadline;
import task.Event;
import task.ToDo;

/**
 * Represents a parser to parse user input.
 */
public class Parser {
    /**
     * Parses the given input and returns the corresponding command.
     *
     * @param input The input to parse.
     * @return The corresponding command.
     */
    public static Command parse(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (command) {
        case "list":
            return new ListCommand();
        case "todo":
            return new AddCommand(new ToDo(arguments));
        case "deadline":
            String[] deadlineParts = arguments.split(" /by ");

            if (deadlineParts.length < 2) {
                return new InvalidCommand("Please enter a valid deadline description!");
            }

            try {
                return new AddCommand(new Deadline(deadlineParts[0], deadlineParts[1]));
            } catch (DateTimeParseException e) {
                return new InvalidCommand(
                        "Invalid date format! Please use yyyy-MM-dd HHmm format.\nExample: 2021-09-30 1800"
                );
            }
        case "event":
            String[] eventParts = arguments.split(" /from | /to ");

            if (eventParts.length < 3) {
                return new InvalidCommand("Please enter a valid event description!");
            }

            try {
                return new AddCommand(new Event(eventParts[0], eventParts[1], eventParts[2]));
            } catch (DateTimeParseException e) {
                return new InvalidCommand(
                        "Invalid date format! Please use yyyy-MM-dd HHmm format.\nExample: 2021-09-30 1800"
                );
            }
        case "mark":
            return new MarkCommand(Integer.parseInt(arguments));
        case "unmark":
            return new UnmarkCommand(Integer.parseInt(arguments));
        case "delete":
            return new DeleteCommand(Integer.parseInt(arguments));
        case "bye":
            return new ExitCommand();
        case "archive":
            return new ArchiveCommand();
        default:
            return new InvalidCommand(
                    "OOPS!!! I'm sorry, but I don't know what that means :-(\nPlease enter a valid command!"
            );
        }
    }
}

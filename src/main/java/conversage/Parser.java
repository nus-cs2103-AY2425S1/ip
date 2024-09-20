package conversage;

import conversage.command.*;
import conversage.exception.ConverSageException;
import conversage.task.Deadline;
import conversage.task.Event;
import conversage.task.ToDo;

/**
 * Represents a parser for parsing user commands.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param fullCommand the user input to be parsed.
     * @return the command corresponding to the user input.
     * @throws ConverSageException if the user input is invalid.
     */
    public static Command parse(String fullCommand) throws ConverSageException {
        String[] commandParts = fullCommand.split(" ", 2);
        String command = commandParts[0];
        int commandLen = commandParts.length;

        switch (command.toLowerCase()) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ByeCommand();
        case "mark":
            if (commandLen < 2) {
                return new ErrorCommand("Please specify which task to mark as done!");
            }
            return new MarkCommand(commandParts[1]);
        case "unmark":
            if (commandLen < 2) {
                return new ErrorCommand("Please specify which task to unmark!");
            }
            return new UnmarkCommand(commandParts[1]);
        case "todo":
            if (commandLen < 2) {
                return new ErrorCommand("Please specify the description of the ToDo task!");
            }
            return new AddCommand(new ToDo(commandParts[1]));
        case "deadline":
            if (commandLen < 2) {
                return new ErrorCommand("Please specify the description and deadline of the task!\n"
                                            + "Use this format for dates: yyyy-MM-dd HH:mm. Use the 'help' command if needed.");
            }
            return new AddCommand(Parser.parseDeadline(commandParts[1]));
        case "event":
            if (commandLen < 2) {
                return new ErrorCommand("Please specify the description and event time (from and to)!\n"
                                            + "Use this format for dates: yyyy-MM-dd HH:mm. Use the 'help' command if needed.");
            }
            return new AddCommand(Parser.parseEvent(commandParts[1]));
        case "delete":
            if (commandLen < 2) {
                return new ErrorCommand("Please specify which task to delete!");
            }
            return new DeleteCommand(commandParts[1]);
        case "find":
            if (commandLen < 2) {
                return new ErrorCommand("Please specify the keyword to find tasks!");
            }
            return new FindCommand(commandParts[1]);
        case "help":
            return new HelpCommand();
        default:
            throw new ConverSageException("Invalid command, please try again");
        }
    }

    /**
     * Parses a deadline task from the given input string.
     *
     * @param input the input string containing the task description and deadline.
     * @return the parsed deadline task.
     * @throws ConverSageException if the input string is invalid.
     */
    private static Deadline parseDeadline(String input) throws ConverSageException {
        if (!input.contains(" /by ")) {
            throw new ConverSageException("The deadline task requires a description and a deadline.");
        }
        String[] parts = input.split(" /by ");
        return new Deadline(parts[0], parts[1]);
    }

    /**
     * Parses an event task from the given input string.
     *
     * @param input the input string containing the task description, start time, and end time.
     * @return the parsed event task.
     * @throws ConverSageException if the input string is invalid.
     */
    private static Event parseEvent(String input) throws ConverSageException {
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new ConverSageException("The event task requires a description, start time, and an end time");
        }
        String[] parts = input.split(" /from | /to ");

        return new Event(parts[0], parts[1], parts[2]);
    }
}

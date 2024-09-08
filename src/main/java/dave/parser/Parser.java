package dave.parser;

import dave.command.AddCommand;
import dave.command.Command;
import dave.command.DeleteCommand;
import dave.command.ExitCommand;
import dave.command.FindCommand;
import dave.command.ListCommand;
import dave.command.MarkCommand;
import dave.command.UnmarkCommand;
import dave.exceptions.InvalidCommandException;
import dave.exceptions.InvalidDateTimeFormatException;
import dave.exceptions.InvalidDescriptionException;
import dave.task.Deadline;
import dave.task.Event;
import dave.task.Todo;

/**
 * The {@code Parser} class is responsible for interpreting and processing user input commands.
 * It determines which type of task the command refers to and creates the corresponding command object.
 */
public class Parser {

    /**
     * Enum representing different task types or commands that the parser can handle.
     */
    public enum TaskType {
        bye, list, mark, unmark, todo, deadline, event, delete, find
    }

    /**
     * Parses the user's input command and returns the appropriate {@code Command} object based on the task type.
     *
     * @param fullCommand The full string command input provided by the user.
     * @return The corresponding {@code Command} object for the input command.
     * @throws InvalidCommandException if the command is not recognized.
     */
    public static Command parse(String fullCommand) throws InvalidCommandException, InvalidDescriptionException {
        String[] commandParts = fullCommand.split(" ", 2);
        String commandWord = commandParts[0];
        String commandArgs = commandParts.length > 1 ? commandParts[1] : "";

        TaskType taskType;
        try {
            taskType = TaskType.valueOf(commandWord);
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException(
                    "Are you joking ? Here are the missions "
                            + "I accept: todo, deadline, event, list, mark, unmark, bye"
            );
        }

        try {
            switch (taskType) {
            case todo:
                return new AddCommand(new Todo(commandArgs));
            case deadline:
                try {
                    return new AddCommand(new Deadline(commandArgs));
                } catch (InvalidDateTimeFormatException e) {
                    System.out.println(e.getMessage());
                    return null;
                }
            case event:
                try {
                    return new AddCommand(new Event(commandArgs));
                } catch (InvalidDateTimeFormatException e) {
                    System.out.println(e.getMessage());
                    return null;
                }
            case delete:
                return new DeleteCommand(Integer.parseInt(commandArgs));
            case mark:
                return new MarkCommand(Integer.parseInt(commandArgs));
            case unmark:
                return new UnmarkCommand(Integer.parseInt(commandArgs));
            case find:
                return new FindCommand(commandArgs);
            case list:
                return new ListCommand();
            case bye:
                return new ExitCommand();
            default:
                return null;
            }
        } catch (InvalidDescriptionException e) {
            throw e;
        }
    }
}

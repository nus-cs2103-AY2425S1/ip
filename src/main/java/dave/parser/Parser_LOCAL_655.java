package dave.parser;

import java.util.function.Function;

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
<<<<<<< HEAD
     * @throws InvalidDescriptionException if the command arguments are invalid.
     */
    public static Command parse(String fullCommand) throws InvalidCommandException, InvalidDescriptionException {
        String[] commandParts = splitCommand(fullCommand);
=======
     * @throws InvalidDescriptionException if the task description is invalid.
     */
    public static Command parse(String fullCommand) throws InvalidCommandException, InvalidDescriptionException {
        assert fullCommand != null && !fullCommand.isEmpty() : "User input should not be null or empty";

        String[] commandParts = fullCommand.split(" ", 2);
>>>>>>> master
        String commandWord = commandParts[0];
        String commandArgs = commandParts[1]; // Now properly extracted even if empty

        TaskType taskType = getTaskType(commandWord);

        return handleTaskType(taskType, commandArgs); // Refactored handling logic
    }

    /**
     * Splits the full command into command word and command arguments.
     *
     * @param fullCommand The user's full input command.
     * @return A string array with the command word and the arguments.
     */
    private static String[] splitCommand(String fullCommand) {
        String[] commandParts = fullCommand.split(" ", 2);
        String commandArgs = commandParts.length > 1 ? commandParts[1] : "";
        return new String[]{commandParts[0], commandArgs}; // Ensure both parts are always present
    }

<<<<<<< HEAD
    /**
     * Handles the task type processing based on the provided task type.
     *
     * @param taskType The task type derived from the command word.
     * @param commandArgs The arguments passed along with the command.
     * @return The corresponding {@code Command} object.
     * @throws InvalidDescriptionException if the task arguments are invalid.
     */
    private static Command handleTaskType(TaskType taskType, String commandArgs) throws InvalidDescriptionException, InvalidCommandException {
        switch (taskType) {
        case todo:
            return new AddCommand(new Todo(commandArgs));
        case deadline:
            return handleDeadline(commandArgs);
        case event:
            return handleEvent(commandArgs);
        case delete:
            return handleNumberCommand(commandArgs, DeleteCommand::new);
        case mark:
            return handleNumberCommand(commandArgs, MarkCommand::new);
        case unmark:
            return handleNumberCommand(commandArgs, UnmarkCommand::new);
        case find:
            return new FindCommand(commandArgs);
        case list:
            return new ListCommand();
        case bye:
            return new ExitCommand();
        default:
            throw new InvalidCommandException("Unsupported command type.");
=======
        TaskType taskType;
        try {
            taskType = TaskType.valueOf(commandWord);
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException(
                    "Are you joking? Here are the missions "
                            + "I accept: todo, deadline, event, list, mark, unmark, delete, find, bye"
            );
>>>>>>> master
        }
    }

<<<<<<< HEAD
    /**
     * Handles the deadline command and wraps potential date-time parsing exceptions.
     *
     * @param commandArgs The arguments for the deadline task.
     * @return A new {@code AddCommand} with a {@code Deadline} task.
     * @throws InvalidDescriptionException if date-time format is invalid.
     */
    private static Command handleDeadline(String commandArgs) throws InvalidDescriptionException {
        try {
            return new AddCommand(new Deadline(commandArgs));
        } catch (InvalidDateTimeFormatException e) {
            throw new InvalidDescriptionException(e.getMessage());
        }
    }

    /**
     * Handles the event command and wraps potential date-time parsing exceptions.
     *
     * @param commandArgs The arguments for the event task.
     * @return A new {@code AddCommand} with an {@code Event} task.
     * @throws InvalidDescriptionException if date-time format is invalid.
     */
    private static Command handleEvent(String commandArgs) throws InvalidDescriptionException {
        try {
            return new AddCommand(new Event(commandArgs));
        } catch (InvalidDateTimeFormatException e) {
            throw new InvalidDescriptionException(e.getMessage());
        }
    }

    /**
     * Gets the {@code TaskType} based on the user's command word.
     *
     * @param commandWord The command word input by the user (e.g., "todo", "deadline").
     * @return The corresponding {@code TaskType}.
     * @throws InvalidCommandException if the command word does not match any known task type.
     */
    private static TaskType getTaskType(String commandWord) throws InvalidCommandException {
        try {
            return TaskType.valueOf(commandWord);
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("Are you joking? Here are the missions I accept: todo, deadline, event, list, mark, unmark, bye.");
        }
    }

    /**
     * Handles commands that require numerical arguments (such as mark, unmark, delete) and ensures proper formatting.
     *
     * @param commandArgs The string containing the numerical argument for the command.
     * @param constructor A function that constructs the command using the integer argument.
     * @return The constructed {@code Command} object.
     * @throws InvalidDescriptionException If the numerical argument is improperly formatted or missing.
     */
    private static Command handleNumberCommand(String commandArgs, Function<Integer, Command> constructor) throws InvalidDescriptionException {
        try {
            return constructor.apply(Integer.parseInt(commandArgs));
        } catch (NumberFormatException e) {
            throw new InvalidDescriptionException("Please provide a valid number.");
=======
        assert taskType != null : "TaskType should not be null at this point";

        switch (taskType) {
        case todo:
            if (commandArgs.isEmpty()) {
                throw new InvalidDescriptionException("Description for a todo task cannot be empty.");
            }
            return new AddCommand(new Todo(commandArgs));

        case deadline:
            try {
                if (commandArgs.isEmpty()) {
                    throw new InvalidDescriptionException("Deadline task must include a date and time.");
                }
                return new AddCommand(new Deadline(commandArgs));
            } catch (InvalidDateTimeFormatException e) {
                System.out.println(e.getMessage());
                return null;
            }

        case event:
            try {
                if (commandArgs.isEmpty()) {
                    throw new InvalidDescriptionException("Event task must include a start and end time.");
                }
                return new AddCommand(new Event(commandArgs));
            } catch (InvalidDateTimeFormatException e) {
                System.out.println(e.getMessage());
                return null;
            }

        case delete:
            if (commandArgs.isEmpty()) {
                throw new InvalidDescriptionException("Please provide the task number to delete.");
            }
            return new DeleteCommand(Integer.parseInt(commandArgs));

        case mark:
            if (commandArgs.isEmpty()) {
                throw new InvalidDescriptionException("Please provide the task number to mark as done.");
            }
            return new MarkCommand(Integer.parseInt(commandArgs));

        case unmark:
            if (commandArgs.isEmpty()) {
                throw new InvalidDescriptionException("Please provide the task number to unmark.");
            }
            return new UnmarkCommand(Integer.parseInt(commandArgs));

        case find:
            if (commandArgs.isEmpty()) {
                throw new InvalidDescriptionException("Please provide a keyword to search.");
            }
            return new FindCommand(commandArgs);

        case list:
            return new ListCommand();

        case bye:
            return new ExitCommand();

        default:
            return null;
>>>>>>> master
        }
    }
}

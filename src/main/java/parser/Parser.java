package parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import commands.ByeCommand;
import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.FindCommand;
import commands.HelpCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.TodoCommand;
import commands.UnmarkCommand;
import exceptions.DownyException;
import exceptions.InvalidCommandException;
import exceptions.InvalidFormatException;
import exceptions.MissingArgumentException;

/**
 * The {@code Parser} class is responsible for interpreting user input and converting
 * it into executable commands. It parses the input string and determines which command
 * should be executed, providing the appropriate parameters to the command.
 */
public class Parser {

    /**
     * Parses the user's input and returns the corresponding command.
     *
     * @param fullCommand The full command input from the user.
     * @return The {@code Command} object corresponding to the user's input.
     * @throws DownyException If the input is invalid or if required arguments are missing.
     */
    public static Command parse(String fullCommand) throws DownyException {
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0];

        return switch (command) {
            case "bye" -> handleBye();
            case "list" -> handleList();
            case "mark" -> handleMark(parts);
            case "unmark" -> handleUnmark(parts);
            case "delete" -> handleDelete(parts);
            case "todo" -> handleTodo(parts);
            case "deadline" -> handleDeadline(parts);
            case "event" -> handleEvent(parts);
            case "help" -> handleHelp();
            case "find" -> handleFind(parts);
            default -> throw new InvalidCommandException();
        };
    }

    /**
     * Handles the "bye" command.
     *
     * @return A {@code ByeCommand} object to terminate the program.
     */
    private static Command handleBye() {
        return new ByeCommand();
    }

    /**
     * Handles the "list" command.
     *
     * @return A {@code ListCommand} object to list all tasks.
     */
    private static Command handleList() {
        return new ListCommand();
    }

    /**
     * Handles the "mark" command, marking a task as complete.
     *
     * @param parts The user input split into parts.
     * @return A {@code MarkCommand} to mark a task as complete.
     * @throws MissingArgumentException if the task number is missing.
     */
    private static Command handleMark(String[] parts) throws MissingArgumentException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingArgumentException("Mark command requires a task number.\n"
                    + "   Usage: mark <taskNumber>");
        }
        return new MarkCommand(parts[1]);
    }

    /**
     * Handles the "unmark" command, marking a task as incomplete.
     *
     * @param parts The user input split into parts.
     * @return An {@code UnmarkCommand} to mark a task as incomplete.
     * @throws MissingArgumentException if the task number is missing.
     */
    private static Command handleUnmark(String[] parts) throws MissingArgumentException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingArgumentException("Unmark command requires a task number.\n"
                    + "   Usage: unmark <taskNumber>");
        }
        return new UnmarkCommand(parts[1]);
    }

    /**
     * Handles the "delete" command to remove a task.
     *
     * @param parts The user input split into parts.
     * @return A {@code DeleteCommand} to remove the specified task.
     * @throws MissingArgumentException if the task number is missing.
     */
    private static Command handleDelete(String[] parts) throws MissingArgumentException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingArgumentException("Delete command requires a task number.\n"
                    + "   Usage: delete <taskNumber>");
        }
        return new DeleteCommand(parts[1]);
    }

    /**
     * Handles the "todo" command to add a new todo task.
     *
     * @param parts The user input split into parts.
     * @return A {@code TodoCommand} to add a new todo task.
     * @throws MissingArgumentException if the task description is missing.
     */
    private static Command handleTodo(String[] parts) throws MissingArgumentException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingArgumentException("Todo command requires a task description.\n"
                    + "   todo <taskDescription>");
        }
        return new TodoCommand(parts[1]);
    }

    /**
     * Handles the "deadline" command to add a new deadline task.
     *
     * @param parts The user input split into parts.
     * @return A {@code DeadlineCommand} to add a new deadline task.
     * @throws MissingArgumentException, InvalidFormatException if the format is incorrect.
     */
    private static Command handleDeadline(String[] parts) throws MissingArgumentException, InvalidFormatException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingArgumentException("Deadline command requires a task description "
                    + "and a due date.\n" + "   deadline <taskDescription> /by <dueDate>");
        }
        String remainder = parts[1];
        String[] splitParts = remainder.split("/by", 2);
        if (splitParts.length < 2) {
            throw new InvalidFormatException("Deadline command must follow the format: "
                    + "<task> /by <dueDate>.");
        }
        String name = splitParts[0].trim();
        String time = splitParts[1].trim();
        LocalDateTime formattedTime;
        try {
            formattedTime = DateTimeHandler.convertToDateTime(time);
        } catch (NumberFormatException | DateTimeException e) {
            throw new InvalidFormatException("dueDate must follow the format: YYYY/MM/DD HHMM");
        }
        return new DeadlineCommand(name, formattedTime);
    }

    /**
     * Handles the "event" command to add a new event task.
     *
     * @param parts The user input split into parts.
     * @return An {@code EventCommand} to add a new event task.
     * @throws MissingArgumentException, InvalidFormatException if the format is incorrect.
     */
    private static Command handleEvent(String[] parts) throws MissingArgumentException, InvalidFormatException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingArgumentException("Event command requires a task description, "
                    + "start time, and end time.\n   "
                    + "event <taskDescription> /from <startTime> /to <endTime>");
        }
        String remainder = parts[1];
        String[] splitParts = remainder.split("/from", 2);
        if (splitParts.length < 2 || !splitParts[1].contains("/to")) {
            throw new InvalidFormatException("Event command must follow the format: "
                    + "<task> /from <startTime> /to <endTime>.");
        }
        String name = splitParts[0].trim();
        String[] time = splitParts[1].split("/to", 2);
        String startTime = time[0].trim();
        String endTime = time[1].trim();
        LocalDateTime formattedStartTime;
        LocalDateTime formattedEndTime;
        try {
            formattedStartTime = DateTimeHandler.convertToDateTime(startTime);
            formattedEndTime = DateTimeHandler.convertToDateTime(endTime);
        } catch (NumberFormatException | DateTimeException e) {
            throw new InvalidFormatException("startTime and endTime must follow the format: YYYY/MM/DD HHMM");
        }
        return new EventCommand(name, formattedStartTime, formattedEndTime);
    }

    /**
     * Handles the "help" command to display help information.
     *
     * @return A {@code HelpCommand} to display available commands.
     */
    private static Command handleHelp() {
        return new HelpCommand();
    }

    /**
     * Handles the "find" command to search for tasks by keyword.
     *
     * @param parts The user input split into parts.
     * @return A {@code FindCommand} to search for tasks.
     * @throws MissingArgumentException if the keyword is missing.
     */
    private static Command handleFind(String[] parts) throws MissingArgumentException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingArgumentException("Find command requires a keyword.\n"
                    + "   Usage: find <keyword>");
        }
        return new FindCommand(parts[1]);
    }
}

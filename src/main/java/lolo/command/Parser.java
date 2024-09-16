package lolo.command;

import lolo.LoloException;
import lolo.command.*;
import lolo.task.Deadline;
import lolo.task.Event;
import lolo.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses user input commands and returns the corresponding {@code Command} object.
 * This class is responsible for interpreting various command formats and
 * creating appropriate command instances to be executed by the chatbot.
 */
public class Parser {

    /**
     * Parses a string command and returns the corresponding {@code Command} object.
     * Recognizes commands for adding, deleting, marking, unmarking tasks,
     * as well as listing tasks, finding tasks, and tagging tasks.
     * Handles date and time formats for deadlines and events.
     *
     * @param fullCommand The command string input by the user.
     * @return A {@code Command} object representing the user command.
     * @throws LoloException If the command is invalid or the date/time format is incorrect.
     */
    public static Command parse(String fullCommand) throws LoloException {
        try {
            if (fullCommand.equalsIgnoreCase("bye")) {
                return new ExitCommand();
            } else if (fullCommand.equalsIgnoreCase("list")) {
                return new ListCommand();
            } else if (fullCommand.startsWith("todo ")) {
                return handleTodoCommand(fullCommand);
            } else if (fullCommand.startsWith("deadline ")) {
                return handleDeadlineCommand(fullCommand);
            } else if (fullCommand.startsWith("event ")) {
                return handleEventCommand(fullCommand);
            } else if (fullCommand.startsWith("mark ")) {
                return handleMarkCommand(fullCommand);
            } else if (fullCommand.startsWith("unmark ")) {
                return handleUnmarkCommand(fullCommand);
            } else if (fullCommand.startsWith("delete ")) {
                return handleDeleteCommand(fullCommand);
            } else if (fullCommand.startsWith("on ")) {
                return handleListOnDateCommand(fullCommand);
            } else if (fullCommand.startsWith("find ")) {
                return handleFindCommand(fullCommand);
            } else if (fullCommand.startsWith("tag")) {
                return handleTagCommand(fullCommand);
            } else {
                throw new LoloException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DateTimeParseException e) {
            throw new LoloException("The date and time format should be yyyy-mm-dd HHmm.");
        }
    }

    /**
     * Returns an {@code AddCommand} to add a {@code ToDo} task.
     *
     * @param fullCommand The command string input by the user.
     * @return An {@code AddCommand} with a new {@code ToDo} task.
     */

    private static Command handleTodoCommand(String fullCommand) {
        return new AddCommand(new ToDo(fullCommand.substring(5)));
    }

    /**
     * Returns an {@code AddCommand} to add a {@code Deadline} task.
     * The date and time are parsed from the command.
     *
     * @param fullCommand The command string input by the user.
     * @return An {@code AddCommand} with a new {@code Deadline} task.
     * @throws LoloException If the date and time format is incorrect.
     */

    private static Command handleDeadlineCommand(String fullCommand) throws LoloException {
        String[] parts = fullCommand.split(" /by ");
        String description = parts[0].substring(9);
        LocalDateTime by = LocalDateTime.parse(parts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return new AddCommand(new Deadline(description, by));
    }

    private static Command handleEventCommand(String fullCommand) throws LoloException {
        String[] parts = fullCommand.split(" /from ");
        String description = parts[0].substring(6);
        String[] fromTo = parts[1].split(" /to ");
        LocalDateTime from = LocalDateTime.parse(fromTo[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        LocalDateTime to = LocalDateTime.parse(fromTo[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return new AddCommand(new Event(description, from, to));
    }

    private static Command handleMarkCommand(String fullCommand) {
        int taskNumber = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
        return new MarkCommand(taskNumber);
    }

    private static Command handleUnmarkCommand(String fullCommand) {
        int taskNumber = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
        return new UnmarkCommand(taskNumber);
    }

    private static Command handleDeleteCommand(String fullCommand) {
        int taskNumber = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
        return new DeleteCommand(taskNumber);
    }

    private static Command handleListOnDateCommand(String fullCommand) {
        LocalDateTime date = LocalDateTime.parse(fullCommand.substring(3) + " 0000", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return new ListOnDateCommand(date);
    }

    private static Command handleFindCommand(String fullCommand) {
        String keyword = fullCommand.substring(5);
        return new FindCommand(keyword);
    }

    private static Command handleTagCommand(String fullCommand) throws LoloException {
        String[] parts = fullCommand.split(" ", 3);
        if (parts.length < 3) {
            throw new LoloException("Please specify both task number and tag.");
        }
        int taskNumber = Integer.parseInt(parts[1]) - 1;
        String tag = parts[2];
        return new TagCommand(taskNumber, tag);
    }
}

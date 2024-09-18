package bro.parser;

import bro.BroException;
import bro.command.*;
import bro.storage.Storage;
import bro.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a Command parser that reads input from the terminal and returns the corresponding Command.
 */
public class Parser {
    private enum ChatCommand {
        bye,
        list,
        find,
        mark,
        unmark,
        todo,
        deadline,
        event,
        period,
        delete
    }

    private static final List<DateTimeFormatter> DATE_TIME_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("M/d/yyyy HHmm"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy HHmm")
    );

    /**
     * Desired output format for date-time.
     */
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm");

    /**
     * Parses the full command string and returns the corresponding Command object.
     *
     * @param fullCommand The full command string entered by the user.
     * @param taskList    The current list of tasks.
     * @param storage     The storage handler for persisting tasks.
     * @return The Command object corresponding to the parsed command.
     * @throws BroException If the command is invalid or parsing fails.
     */
    public static Command parse(String fullCommand, TaskList taskList, Storage storage) throws BroException {
        String[] inputArgs = fullCommand.split(" ", 2);
        ChatCommand cmd;
        try {
            cmd = ChatCommand.valueOf(inputArgs[0].toLowerCase());
        } catch (IllegalArgumentException e) {
            throw new BroException("I do not support that command");
        }

        String secondArg = (inputArgs.length > 1) ? inputArgs[1].trim() : "";

        switch (cmd) {
        case bye:
            return parseBye();
        case list:
            return parseList(taskList);
        case find:
            return parseFind(taskList, secondArg);
        case mark:
            return parseMark(taskList, storage, secondArg, true);
        case unmark:
            return parseMark(taskList, storage, secondArg, false);
        case todo:
            return parseTodo(taskList, storage, secondArg);
        case deadline:
            return parseDeadline(taskList, storage, secondArg);
        case event:
            return parseEvent(taskList, storage, secondArg);
        case period:
            return parsePeriod(taskList, storage, secondArg);
        case delete:
            return parseDelete(taskList, storage, secondArg);
        default:
            throw new BroException("Unexpected error has occurred");
        }
    }

    /**
     * Handles the 'bye' command.
     *
     * @return An ExitCommand to terminate the application.
     */
    private static Command parseBye() {
        return new ExitCommand();
    }

    /**
     * Handles the 'list' command.
     *
     * @param taskList The current list of tasks.
     * @return A ReadCommand to display all tasks.
     */
    private static Command parseList(TaskList taskList) {
        return new ReadCommand(taskList);
    }

    /**
     * Handles the 'find' command.
     *
     * @param taskList  The current list of tasks.
     * @param query The search query.
     * @return A FindCommand to search for tasks containing the query.
     * @throws BroException If the search query is empty.
     */
    private static Command parseFind(TaskList taskList, String query) throws BroException {
        if (query.isEmpty()) {
            throw new BroException("Empty find query");
        }
        return new FindCommand(taskList, query);
    }

    /**
     * Handles the 'mark' and 'unmark' commands.
     *
     * @param taskList The current list of tasks.
     * @param storage  The storage handler.
     * @param arg      The task ID as a string.
     * @param isMark   True if marking the task, false if unmarking.
     * @return An EditCommand to mark or unmark the specified task.
     * @throws BroException If the task ID is invalid or parsing fails.
     */
    private static Command parseMark(TaskList taskList, Storage storage, String arg, boolean isMark) throws BroException {
        try {
            int taskId = Integer.parseInt(arg) - 1;
            return new EditCommand(taskList, isMark, taskId, storage);
        } catch (NumberFormatException e) {
            throw new BroException("Invalid task ID.");
        } catch (IndexOutOfBoundsException e) {
            throw new BroException("Task ID out of range.");
        } catch (Exception e) {
            throw new BroException(isMark ? "Error marking task." : "Error unmarking task.");
        }
    }

    /**
     * Handles the 'todo' command.
     *
     * @param taskList The current list of tasks.
     * @param storage  The storage handler.
     * @param description The description of the todo task.
     * @return A CreateCommand to add the new TodoTask.
     * @throws BroException If the task description is empty.
     */
    private static Command parseTodo(TaskList taskList, Storage storage, String description) throws BroException {
        if (description.isEmpty()) {
            throw new BroException("bro.Bro I can't add an empty task");
        }
        Task todoTask = new TodoTask(description);
        return new CreateCommand(taskList, todoTask, storage);
    }

    /**
     * Handles the 'deadline' command.
     *
     * @param taskList The current list of tasks.
     * @param storage  The storage handler.
     * @param args     The arguments containing task description and deadline.
     * @return A CreateCommand to add the new DeadlineTask.
     * @throws BroException If input validation fails or deadline parsing fails.
     */
    private static Command parseDeadline(TaskList taskList, Storage storage, String args) throws BroException {
        if (args.isEmpty()) {
            throw new BroException("bro.Bro I Can't add an empty task");
        }
        if (!args.contains("/by")) {
            throw new BroException("Usage: deadline <task> /by <deadline>");
        }

        String[] deadlineInputs = args.split("/by", 2);
        if (deadlineInputs.length < 2) {
            throw new BroException("Usage: deadline <task> /by <deadline>");
        }

        String taskContent = deadlineInputs[0].trim();
        String deadlineInput = deadlineInputs[1].trim();

        String parsedDeadline = parseDateTime(deadlineInput, "deadline");

        Task deadlineTask = new DeadlineTask(taskContent, parsedDeadline);
        return new CreateCommand(taskList, deadlineTask, storage);
    }

    /**
     * Handles the 'event' command.
     *
     * @param taskList The current list of tasks.
     * @param storage  The storage handler.
     * @param args     The arguments containing task description, start time, and end time.
     * @return A CreateCommand to add the new EventTask.
     * @throws BroException If input validation fails.
     */
    private static Command parseEvent(TaskList taskList, Storage storage, String args) throws BroException {
        if (args.isEmpty()) {
            throw new BroException("Empty task provided");
        }
        if (!args.contains("/from") || !args.contains("/to")) {
            throw new BroException("Usage: event <task> /from <startTime> /to <endTime>");
        }

        try {
            String[] eventInputs = args.split("/from", 2);
            String eventName = eventInputs[0].trim();

            String[] durationInputs = eventInputs[1].split("/to", 2);
            if (durationInputs.length < 2) {
                throw new BroException("Usage: event <task> /from <startTime> /to <endTime>");
            }

            String startTimeInput = durationInputs[0].trim();
            String endTimeInput = durationInputs[1].trim();

            String parsedStartTime = parseDateTime(startTimeInput, "start time");
            String parsedEndTime = parseDateTime(endTimeInput, "end time");

            Task eventTask = new EventTask(eventName, parsedStartTime, parsedEndTime);
            return new CreateCommand(taskList, eventTask, storage);
        } catch (BroException e) {
            throw e;
        } catch (Exception e) {
            throw new BroException("Usage: event <task> /from <startTime> /to <endTime>");
        }
    }

    /**
     * Handles the 'period' command.
     *
     * @param taskList The current list of tasks.
     * @param storage  The storage handler.
     * @param args     The arguments containing task description, start date, and end date.
     * @return A CreateCommand to add the new PeriodTask.
     * @throws BroException If input validation fails.
     */
    private static Command parsePeriod(TaskList taskList, Storage storage, String args) throws BroException {
        if (args.isEmpty()) {
            throw new BroException("Empty task provided");
        }
        if (!args.contains("/from") || !args.contains("/to")) {
            throw new BroException("Usage: period <task> /from <startDate> /to <endDate>");
        }

        try {
            String[] periodInputs = args.split("/from", 2);
            String periodName = periodInputs[0].trim();

            String[] durationInputs = periodInputs[1].split("/to", 2);
            if (durationInputs.length < 2) {
                throw new BroException("Usage: period <task> /from <startDate> /to <endDate>");
            }

            String startDateInput = durationInputs[0].trim();
            String endDateInput = durationInputs[1].trim();

            String parsedStartDate = parseDateTime(startDateInput, "start date");
            String parsedEndDate = parseDateTime(endDateInput, "end date");

            Task periodTask = new PeriodTask(periodName, parsedStartDate, parsedEndDate);
            return new CreateCommand(taskList, periodTask, storage);
        } catch (BroException e) {
            throw e;
        } catch (Exception e) {
            throw new BroException("Usage: period <task> /from <startDate> /to <endDate>");
        }
    }

    /**
     * Handles the 'delete' command.
     *
     * @param taskList The current list of tasks.
     * @param storage  The storage handler.
     * @param arg      The task ID as a string.
     * @return A DeleteCommand to remove the specified task.
     * @throws BroException If the task ID is invalid or deletion fails.
     */
    private static Command parseDelete(TaskList taskList, Storage storage, String arg) throws BroException {
        try {
            int taskId = Integer.parseInt(arg) - 1;
            return new DeleteCommand(taskList, taskId, storage);
        } catch (NumberFormatException e) {
            throw new BroException("Invalid task ID.");
        } catch (IndexOutOfBoundsException e) {
            throw new BroException("Task ID out of range.");
        } catch (Exception e) {
            throw new BroException("Deletion Error.");
        }
    }

    /**
     * Parses a date-time string using predefined formatters and returns it in the desired output format.
     *
     * @param input The date-time string to parse.
     * @param field The field being parsed (for error messages).
     * @return The formatted date-time string.
     * @throws BroException If parsing fails for all formatters.
     */
    private static String parseDateTime(String input, String field) throws BroException {
        for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
            try {
                LocalDateTime parsedDate = LocalDateTime.parse(input, formatter);
                return parsedDate.format(OUTPUT_FORMATTER);
            } catch (Exception ignored) {
                // Continue trying other formatters
            }
        }
        throw new BroException("Invalid " + field + " format.");
    }
}


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
 * Represents a Command parser that reads from the terminal and the Command represented
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

    // Desired output format
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm");

    public static Command parse(String fullCommand, TaskList taskList, Storage storage) throws BroException {
        // Parse command
        String[] inputArgs = fullCommand.split(" ", 2);
        ChatCommand cmd;
        try {
            cmd = ChatCommand.valueOf(inputArgs[0]);
        } catch (IllegalArgumentException e) {
            throw new BroException("I do not support that command");
        }
        String secondArg = "";
        if (inputArgs.length > 1) {
            secondArg = inputArgs[1];
        }

        switch (cmd) {
            case bye:
                return new ExitCommand();
            case list:
                return new ReadCommand(taskList);
            case find:
                if (secondArg.isEmpty()) {
                    throw new BroException("Empty find query");
                }
                return new FindCommand(taskList, secondArg);
            case mark:
                try {
                    int taskId = Integer.parseInt(secondArg) - 1;
                    return new EditCommand(taskList, true, taskId, storage);
                } catch (Exception e) {
                    throw new BroException("Error marking task");
                }
            case unmark:
                try {
                    int taskId = Integer.parseInt(secondArg) - 1;
                    return new EditCommand(taskList, false, taskId, storage);
                } catch (Exception e) {
                    throw new BroException("Error unmarking task");
                }
            case todo:
                if (secondArg.isEmpty()) {
                    throw new BroException("bro.Bro I can't add a empty task");
                }
                Task todoTask = new TodoTask(secondArg);
                return new CreateCommand(taskList, todoTask, storage);
            case deadline:
                // Input validation
                if (secondArg.isEmpty()) {
                    throw new BroException("bro.Bro I Can't add a empty task");
                }
                if (!secondArg.contains("/by")) {
                    throw new BroException("Usage: deadline <task> /by <deadline>");
                }
                String[] deadlineInputs = secondArg.split("/by");
                String taskContent = deadlineInputs[0].trim();
                String deadline = deadlineInputs[1].trim();

                // Parse deadline
                String parsedDeadline = deadline;
                for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
                    try {
                        LocalDateTime parsedDate = LocalDateTime.parse(deadline, formatter);
                        parsedDeadline = parsedDate.format(OUTPUT_FORMATTER);
                        break;
                    } catch (Exception ignored) {
                    }
                }
                Task deadlineTask = new DeadlineTask(taskContent, parsedDeadline);
                return new CreateCommand(taskList, deadlineTask, storage);
            case event:
                // Input validation
                if (secondArg.isEmpty()) {
                    throw new BroException("Empty task provided");
                }
                if (!secondArg.contains("/from") || !secondArg.contains("/to")) {
                    throw new BroException("Usage: event <task> /from <startTime> /to <endTime>");
                }

                try {
                    String[] eventInputs = secondArg.split("/from");
                    String eventName = eventInputs[0].trim();

                    String[] durationInputs = eventInputs[1].split("/to");
                    String startTime = durationInputs[0].trim();
                    String endTime = durationInputs[1].trim();
                    Task eventTask = new EventTask(eventName, startTime, endTime);
                    return new CreateCommand(taskList, eventTask, storage);
                } catch (Exception e) {
                    throw new BroException("Usage: event <task> /from <startTime> /to <endTime>");
                }
            case period:
                // Input validation
                if (secondArg.isEmpty()) {
                    throw new BroException("Empty task provided");
                }
                if (!secondArg.contains("/from") || !secondArg.contains("/to")) {
                    throw new BroException("Usage: period <task> /from <startDate> /to <endDate>");
                }

                try {
                    String[] eventInputs = secondArg.split("/from");
                    String eventName = eventInputs[0].trim();

                    String[] durationInputs = eventInputs[1].split("/to");
                    String startDate = durationInputs[0].trim();
                    String endDate = durationInputs[1].trim();
                    Task periodTask = new PeriodTask(eventName, startDate, endDate);
                    return new CreateCommand(taskList, periodTask, storage);
                } catch (Exception e) {
                    throw new BroException("Usage: period <task> /from <startDate> /to <endDate>");
                }
            case delete:
                try {
                    int taskId = Integer.parseInt(secondArg) - 1;
                    return new DeleteCommand(taskList, taskId, storage);
                } catch (Exception e) {
                    throw new BroException("Deletion Error.");
                }
        }
        throw new BroException("Unexpected error has occurred");
    }

}

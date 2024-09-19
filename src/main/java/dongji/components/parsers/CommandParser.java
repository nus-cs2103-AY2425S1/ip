package dongji.components.parsers;

import java.time.format.DateTimeParseException;

import dongji.Dongji;
import dongji.components.DateTimeData;
import dongji.components.commands.ByeCommand;
import dongji.components.commands.Command;
import dongji.components.commands.DeadlineCommand;
import dongji.components.commands.DeleteCommand;
import dongji.components.commands.EventCommand;
import dongji.components.commands.FindCommand;
import dongji.components.commands.ListCommand;
import dongji.components.commands.MarkCommand;
import dongji.components.commands.RecurringCommand;
import dongji.components.commands.TodoCommand;
import dongji.components.commands.UnmarkCommand;
import dongji.components.tasks.TaskList;
import dongji.exceptions.DongjiParseException;
import dongji.exceptions.DongjiUnknownInstructionException;

public class CommandParser {
    private TaskList taskList;
    private Dongji dongji;

    public CommandParser(TaskList taskList, Dongji dongji) {
        this.taskList = taskList;
        this.dongji = dongji;
    }

    /**
     * Parses the command string into a corresponding Command object
     * 
     * @param commandString
     * @return Command
     * @throws DongjiUnknownInstructionException
     * @throws DongjiParseException
     */
    public Command parseToCommand(String commandString) throws DongjiUnknownInstructionException, DongjiParseException {
        String command = commandString.split(" ")[0];

        switch (command) {
        case "list":
            return new ListCommand(this.taskList);
        case "delete":
            int deleteIndex = this.parseIndex(commandString);
            return new DeleteCommand(this.taskList, deleteIndex);
        case "mark":
            int markIndex = this.parseIndex(commandString);
            return new MarkCommand(this.taskList, markIndex);
        case "unmark":
            int unmarkIndex = this.parseIndex(commandString);
            return new UnmarkCommand(this.taskList, unmarkIndex);
        case "find":
            String keyword = this.parseKeyword(commandString);
            return new FindCommand(this.taskList, keyword);
        case "todo":
            String taskName = this.extractTaskName(commandString);
            return new TodoCommand(this.taskList, taskName);
        case "event":
            if (!isCommandStringValidEvent(commandString)) {
                throw new DongjiParseException(
                        "Invalid command format for event. Please follow the format: "
                                + "event <task name> /from <yyyy-mm-dd [hhmm; optional]>"
                                + " /to <yyyy-mm-dd [hhmm; optional]>");
            }
            taskName = this.extractTaskName(commandString);

            DateTimeData eventStartDate;
            DateTimeData eventEndDate;

            eventStartDate = this.extractEventStartDate(commandString);
            eventEndDate = this.extractEventEndDate(commandString);

            if (isEventEndEarlierThanStart(eventStartDate, eventEndDate)) {
                throw new DongjiParseException(
                        "Event end date is earlier than start date. Please provide a valid dates");
            }

            return new EventCommand(this.taskList, taskName, eventStartDate, eventEndDate);
        case "deadline":
            if (!isCommandStringValidDeadline(commandString)) {
                throw new DongjiParseException(
                        "Invalid command format for deadline. Please follow the format: "
                                + "deadline <task name> /by <yyyy-mm-dd [hhmm; optional]>");
            }
            taskName = this.extractTaskName(commandString);
            DateTimeData deadlineDate = this.extractDeadline(commandString);
            return new DeadlineCommand(this.taskList, taskName, deadlineDate);
        case "recur":
            if (!isCommandStringValidRecurring(commandString)) {
                throw new DongjiParseException(
                        "Invalid command format for recurring. Please follow the format: "
                                + "recur <task name> /cron <* * * * *>");
            }

            taskName = this.extractTaskName(commandString);
            String cron = commandString.split(" /cron ")[1].trim();
            return new RecurringCommand(this.taskList, taskName, cron);
        case "bye":
            return new ByeCommand(this.dongji);
        default:
            throw new DongjiUnknownInstructionException("Unknown instruction! Please provide a valid instruction");
        }
    }

    private boolean isCommandStringValidRecurring(String commandString) {
        return commandString.contains("/cron") && this.hasValidCron(commandString);
    }

    private boolean hasValidCron(String commandString) {
        String cron = commandString.split(" /cron ")[1].trim();
        String[] cronParts = cron.split(" ");

        return cronParts.length == 5;
    }

    private boolean isEventEndEarlierThanStart(DateTimeData startDate, DateTimeData endDate) {
        assert startDate != null;
        assert endDate != null;

        return startDate.compareTo(endDate) > 0;
    }

    private boolean isCommandStringValidEvent(String commandString) {
        return (commandString.contains("/from") && commandString.contains("/to"));
    }

    private boolean isCommandStringValidDeadline(String commandString) {
        return commandString.contains("/by");
    }

    private int parseIndex(String commandString) {
        assert commandString.split(" ").length >= 2;
        return Integer.parseInt(commandString.split(" ")[1]) - 1;
    }

    private String parseKeyword(String commandString) {
        assert commandString.split(" ", 2).length >= 2;
        return commandString.split(" ", 2)[1];
    }

    private String extractTaskName(String commandString) {
        assert commandString.split(" ", 2).length >= 2;
        String afterCommand = commandString.split(" ", 2)[1];

        assert afterCommand.split(" /", 2).length >= 1;
        return afterCommand.split(" /", 2)[0];
    }

    private DateTimeData extractDeadline(String commandString) throws DongjiParseException {
        assert commandString.split(" /by ", 2).length >= 2;
        String deadlineDateTimeString = commandString.split(" /by ", 2)[1];
        DateTimeData deadlineData;

        try {
            deadlineData = DateTimeParser.extractDateTime(deadlineDateTimeString);
        } catch (DateTimeParseException e) {
            throw new DongjiParseException(
                    "Invalid date format for deadline. Please follow the format: "
                            + "deadline <task name> /by <yyyy-mm-dd [hhmm; optional]>");
        }

        return deadlineData;
    }

    private DateTimeData extractEventStartDate(String commandString) throws DongjiParseException {
        assert commandString.split(" /from ", 2).length >= 2;
        String afterFrom = commandString.split(" /from ", 2)[1];

        assert afterFrom.split(" /to ", 2).length >= 1;
        String beforeTo = afterFrom.split(" /to ", 2)[0];
        DateTimeData startDateData;

        try {
            startDateData = DateTimeParser.extractDateTime(beforeTo);
        } catch (DateTimeParseException e) {
            throw new DongjiParseException(
                    "Invalid date format for event start date. Please follow the format: " 
                            + "event <task name> /from <yyyy-mm-dd [hhmm; optional]>"
                            + " /to <yyyy-mm-dd [hhmm; optional]>");
        }

        return startDateData;
    }

    private DateTimeData extractEventEndDate(String commandString) throws DongjiParseException {
        assert commandString.split(" /to ", 2).length >= 2;
        String afterTo = commandString.split(" /to ", 2)[1];
        DateTimeData endDateData;

        try {
            endDateData = DateTimeParser.extractDateTime(afterTo);
        } catch (DateTimeParseException e) {
            throw new DongjiParseException(
                    "Invalid date format for event end date. Please follow the format: "
                            + "event <task name> /from <yyyy-mm-dd [hhmm; optional]> "
                            + "/to <yyyy-mm-dd [hhmm; optional]>");
        }

        return endDateData;
    }
}

package hypebot.parser;

import hypebot.command.AddCommand;
import hypebot.command.ByeCommand;
import hypebot.command.Command;
import hypebot.command.DeleteCommand;
import hypebot.command.FindCommand;
import hypebot.command.GreetCommand;
import hypebot.command.HappeningCommand;
import hypebot.command.HelpCommand;
import hypebot.command.ListCommand;
import hypebot.command.MarkCommand;
import hypebot.command.UnknownCommand;
import hypebot.command.UnmarkCommand;
import hypebot.task.Deadline;
import hypebot.task.Event;
import hypebot.task.ToDo;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static hypebot.common.Messages.ERROR_DEADLINE_DATE_MISSING;
import static hypebot.common.Messages.ERROR_EVENT_TIME_MISSING;
import static hypebot.common.Messages.ERROR_HAPPENING_DATE_MISSING;
import static hypebot.common.Messages.ERROR_HAPPENING_DATE_WRONG_FORMAT;
import static hypebot.common.Messages.ERROR_TASK_NAME_EMPTY;
import static hypebot.common.Messages.ERROR_DELETE_TASK_INDEX_MISSING;
import static hypebot.common.Messages.ERROR_MARK_TASK_INDEX_MISSING;
import static hypebot.common.Messages.ERROR_UNMARK_TASK_INDEX_MISSING;
import static hypebot.common.Messages.ERROR_SEARCH_QUERY_EMPTY;

/**
 * Represents the Parser which makes sense of user input read form the user interface
 * and returns the corresponding Command to be executed by HypeBot.
 *
 * @author YoungseoPark (@youngseopark05)
 */
public class Parser {
    private static final int INDEX_OFFSET = 1;

    private static int getIndexOffset(String line) throws NumberFormatException {
        return Integer.parseInt(line.strip()) - INDEX_OFFSET;
    }

    /**
     * Takes in the line read from the user interface and returns appropriate Command to execute.
     *
     * @param fullCommand Line read from user interface, given by Ui.readCommand().
     * @return Corresponding Command object pending execution.
     * @throws ParseException If a Task's name, Deadline's due date, Event's start or end time,
     *                        or date to search tasks for is missing.
     * @throws NumberFormatException If index of Task to mark, unmark, or delete is not a number.
     * @throws DateTimeParseException If Deadline due date, Event start/end time, or search date not properly formatted.
     * @throws IndexOutOfBoundsException If index of Task to mark, unmark, or delete too low/high.
     */
    public static Command parse(String fullCommand) throws ParseException,
            NumberFormatException, DateTimeParseException, IndexOutOfBoundsException {
        String[] splitLineForDates = fullCommand.split(" /");
        String[] commandAndTaskName = splitLineForDates[0].split(" ");
        String command = commandAndTaskName[0];
        StringBuilder taskNameBuilder = new StringBuilder();
        for (int i = 1; i < commandAndTaskName.length; i++) {
            taskNameBuilder.append(commandAndTaskName[i]).append(" ");
        }
        String taskName = taskNameBuilder.toString();

        switch (command) {
        case "start":
            return new GreetCommand();
        case "help":
            return new HelpCommand();
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "todo":
            if (taskName.isEmpty()) {
                throw new ParseException(ERROR_TASK_NAME_EMPTY, 0);
            }
            ToDo newTodo = new ToDo(taskName);
            return new AddCommand(newTodo);
        case "deadline":
            if (splitLineForDates.length < 2) {
                throw new ParseException(ERROR_DEADLINE_DATE_MISSING, 0);
            }
            Deadline newDeadline = new Deadline(taskName, splitLineForDates[1]);
            return new AddCommand(newDeadline);
        case "event":
            if (splitLineForDates.length < 3) {
                throw new ParseException(ERROR_EVENT_TIME_MISSING, 0);
            }
            Event newEvent = new Event(taskName, splitLineForDates[1], splitLineForDates[2]);
            return new AddCommand(newEvent);
        case "find":
            if (taskName.isEmpty()) {
                throw new ParseException(ERROR_SEARCH_QUERY_EMPTY, 0);
            }
            return new FindCommand(taskName);
        case "mark":
            try {
                int idxToMark = getIndexOffset(taskName);
                return new MarkCommand(idxToMark);
            } catch (NumberFormatException e) {
                throw new NumberFormatException(ERROR_MARK_TASK_INDEX_MISSING);
            }
        case "unmark":
            try {
                int idxToUnmark = getIndexOffset(taskName);
                return new UnmarkCommand(idxToUnmark);
            } catch (NumberFormatException e) {
                throw new NumberFormatException(ERROR_UNMARK_TASK_INDEX_MISSING);
            }
        case "delete":
            try {
                int idxToDelete = getIndexOffset(taskName);
                return new DeleteCommand(idxToDelete);
            } catch (NumberFormatException e) {
                throw new NumberFormatException(ERROR_DELETE_TASK_INDEX_MISSING);
            }
        case "happening":
            if (splitLineForDates.length < 2) {
                throw new ParseException(ERROR_HAPPENING_DATE_MISSING, 0);
            }
            try {
                LocalDate searchDate = LocalDate.parse(splitLineForDates[1],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return new HappeningCommand(searchDate);
            } catch (DateTimeParseException e) {
                throw new DateTimeParseException(ERROR_HAPPENING_DATE_WRONG_FORMAT,
                        e.getParsedString(), e.getErrorIndex());
            }
        default:
            return new UnknownCommand(command);
        }
    }
}

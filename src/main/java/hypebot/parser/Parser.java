package hypebot.parser;

import hypebot.command.*;
import hypebot.task.Deadline;
import hypebot.task.Event;
import hypebot.task.TaskDateTimeParseException;
import hypebot.task.ToDo;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static hypebot.common.Messages.*;

public class Parser {
    private static final int INDEX_OFFSET = 1;

    private static int getIndexOffset(String line) throws NumberFormatException, IndexOutOfBoundsException {
        return Integer.parseInt(line.strip()) - INDEX_OFFSET;
    }

    public static Command parse(String fullCommand) throws ParseException,
            NumberFormatException, IndexOutOfBoundsException, DateTimeParseException {
        String[] splitLineForDates = fullCommand.split(" /");
        String[] commandAndTaskName = splitLineForDates[0].split(" ");
        String command = commandAndTaskName[0];
        StringBuilder taskNameBuilder = new StringBuilder();
        for (int i = 1; i < commandAndTaskName.length; i++) {
            taskNameBuilder.append(commandAndTaskName[i]).append(" ");
        }
        String taskName = taskNameBuilder.toString();

        switch (command) {
        case "help":
            return new HelpCommand();
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "todo":
            if (taskName.isEmpty()) {
                throw new ParseException(TASK_NAME_EMPTY_ERROR, 0);
            }
            ToDo newTodo = new ToDo(taskName);
            return new AddCommand(newTodo);
        case "deadline":
            if (splitLineForDates.length < 2) {
                throw new ParseException(DUE_DATE_MISSING_ERROR, 0);
            }
            Deadline newDeadline = new Deadline(taskName, splitLineForDates[1]);
            return new AddCommand(newDeadline);
        case "event":
            if (splitLineForDates.length < 3) {
                throw new ParseException(EVENT_TIME_MISSING_ERROR, 0);
            }
            Event newEvent = new Event(taskName, splitLineForDates[1], splitLineForDates[2]);
            return new AddCommand(newEvent);
        case "mark":
            try {
                int idxToMark = getIndexOffset(taskName);
                return new MarkCommand(idxToMark);
            } catch (NumberFormatException e) {
                throw new NumberFormatException(TASK_NUMBER_TO_MARK_MISSING_ERROR);
            } catch (IndexOutOfBoundsException e) {
                throw new ArrayIndexOutOfBoundsException(TASK_NUMBER_TO_MARK_OUT_OF_BOUNDS_ERROR);
            }
        case "unmark":
            try {
                int idxToUnmark = getIndexOffset(taskName);
                return new UnmarkCommand(idxToUnmark);
            } catch (NumberFormatException e) {
                throw new NumberFormatException(TASK_NUMBER_TO_UNMARK_MISSING_ERROR);
            } catch (IndexOutOfBoundsException e) {
                throw new ArrayIndexOutOfBoundsException(TASK_NUMBER_TO_UNMARK_OUT_OF_BOUNDS_ERROR);
            }
        case "delete":
            try {
                int idxToDelete = getIndexOffset(taskName);
                return new DeleteCommand(idxToDelete);
            } catch (NumberFormatException e) {
                throw new NumberFormatException(TASK_NUMBER_TO_DELETE_MISSING_ERROR);
            } catch (IndexOutOfBoundsException e) {
                throw new IndexOutOfBoundsException(TASK_NUMBER_TO_DELETE_OUT_OF_BOUNDS_ERROR);
            }
        case "happening":
            if (splitLineForDates.length < 2) {
                throw new ParseException(SEARCH_DATE_MISSING_ERROR, 0);
            }
            try {
                LocalDate searchDate = LocalDate.parse(splitLineForDates[1],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return new HappeningCommand(searchDate);
            } catch (DateTimeParseException e) {
                throw new DateTimeParseException(SEARCH_DATE_PARSE_ERROR, e.getParsedString(), e.getErrorIndex());
            }
        default:
            return new UnknownCommand(command);
        }
    }
}

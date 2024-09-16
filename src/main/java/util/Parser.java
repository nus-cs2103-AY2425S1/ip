package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import command.AddCommand;
import command.AddMultipleTodosCommand;
import command.Command;
import command.CommandType;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.HelpCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import exception.InvalidCommandException;
import exception.ScheduloException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;


/**
 * The Parser class is responsible for parsing user input commands into executable Command objects.
 * It also provides methods to parse individual tasks from storage data.
 */
public class Parser {

    /**
     * Parses the user input command and returns the corresponding Command object.
     *
     * @param fullCommand The full user input string.
     * @return The corresponding Command object.
     * @throws ScheduloException If the command is invalid or the input is malformed.
     */
    public static Command parse(String fullCommand) throws ScheduloException {

        CommandType commandType;
        String[] splitWords;

        try {
            splitWords = fullCommand.trim().split(" ", 2);
            assert splitWords.length > 0 : "Split words should not be empty";
            commandType = CommandType.valueOf(splitWords[0].toUpperCase());
        } catch (Exception e) {
            throw new InvalidCommandException();
        }

        switch (commandType) {
        case LIST:
            return new ListCommand();
        case MARK:
            return parseMarkCommand(splitWords);
        case UNMARK:
            return parseUnmarkCommand(splitWords);
        case DELETE:
            return parseDeleteCommand(splitWords);
        case TODO:
            if (splitWords.length < 2 || splitWords[1].trim().isEmpty()) {
                throw new InvalidCommandException();
            }
            return new AddCommand(new Todo(splitWords[1]));
        case DEADLINE:
            if (splitWords.length < 2 || splitWords[1].trim().isEmpty()) {
                throw new InvalidCommandException();
            }
            return parseDeadlineCommand(splitWords[1]);
        case EVENT:
            if (splitWords.length < 2 || splitWords[1].trim().isEmpty()) {
                throw new InvalidCommandException();
            }
            return parseEventCommand(splitWords[1]);
        case FIND:
            if (splitWords.length < 2 || splitWords[1].trim().isEmpty()) {
                throw new InvalidCommandException();
            }
            return new FindCommand(splitWords[1]);
        case ADDMULTIPLETODOS:
            if (splitWords.length < 2 || splitWords[1].trim().isEmpty()) {
                throw new InvalidCommandException();
            }
            return new AddMultipleTodosCommand(splitWords[1].split(","));
        case HELP:
            return new HelpCommand();
        case BYE:
            return new ExitCommand();
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Parses the details of a mark command and returns the corresponding Command object.
     *
     * @param splitWords takes in a string of parts of the command
     * @return the Command type to parse the command
     * @throws ScheduloException which can display custom error messages
     */
    private static Command parseMarkCommand(String[] splitWords) throws ScheduloException {
        if (splitWords.length < 2 || splitWords[1].trim().isEmpty()) {
            throw new InvalidCommandException();
        }
        try {
            int index = Integer.parseInt(splitWords[1]);
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }
    }

    /**
     * Parses the details of an unmark command and returns the corresponding Command object.
     *
     * @param splitWords takes in the parts of the unmark command
     * @return the unmark command
     * @throws ScheduloException incase of errors with custom message
     */

    private static Command parseUnmarkCommand(String[] splitWords) throws ScheduloException {
        if (splitWords.length < 2 || splitWords[1].trim().isEmpty()) {
            throw new InvalidCommandException();
        }
        try {
            int index = Integer.parseInt(splitWords[1]);
            return new UnmarkCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }
    }

    /**
     * Parses the details of a delete command and returns the corresponding Command object.
     *
     * @param splitWords to parse the delete command
     * @return the delete command
     * @throws ScheduloException to handle exceptions with custom messages
     */

    private static Command parseDeleteCommand(String[] splitWords) throws ScheduloException {
        if (splitWords.length < 2 || splitWords[1].trim().isEmpty()) {
            throw new InvalidCommandException();
        }
        try {
            int index = Integer.parseInt(splitWords[1]);
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }
    }

    /**
     * Parses the details of a deadline command and returns the corresponding Command object.
     *
     * @param details The details of the deadline command, including the task description and deadline.
     * @return The corresponding Command object for the deadline.
     * @throws ScheduloException If the input is invalid or the date format is incorrect.
     */
    private static Command parseDeadlineCommand(String details) throws ScheduloException {
        String[] taskParts = details.split(" /by ", 2);
        validateTaskParts(taskParts, "The description or /by of a deadline cannot be empty.");

        String[] dateParts = taskParts[1].split(" ");
        return createDeadlineCommand(taskParts[0], dateParts);
    }

    private static void validateTaskParts(String[] taskParts, String message) throws ScheduloException {
        if (taskParts.length < 2 || taskParts[0].trim().isEmpty()) {
            throw new ScheduloException(message);
        }
    }

    private static Command createDeadlineCommand(String description, String[] dateParts) throws ScheduloException {
        try {
            if (dateParts.length == 1) {
                return createDeadlineWithDate(description, dateParts[0]);
            } else if (dateParts.length == 2) {
                return createDeadlineWithDateTime(description, dateParts[0], dateParts[1]);
            } else {
                throw new ScheduloException("The /by of a deadline should be in the format yyyy-MM-dd or yyyy-MM-dd HH:mm.");
            }
        } catch (DateTimeParseException e) {
            throw new ScheduloException("Invalid date format. Please use 'yyyy-MM-dd' for dates or 'yyyy-MM-dd HH:mm' for date and time.");
        }
    }

    private static Command createDeadlineWithDate(String description, String date) throws ScheduloException {
        LocalDate deadlineDate = LocalDate.parse(date);
        if (deadlineDate.isBefore(LocalDate.now())) {
            throw new ScheduloException("The deadline cannot be in the past.");
        }
        return new AddCommand(new Deadline(description, deadlineDate));
    }

    private static Command createDeadlineWithDateTime(String description, String date, String time) throws ScheduloException {
        LocalDateTime deadlineDateTime = LocalDateTime.parse(date + "T" + time);
        if (deadlineDateTime.isBefore(LocalDateTime.now())) {
            throw new ScheduloException("The deadline cannot be in the past.");
        }
        return new AddCommand(new Deadline(description, deadlineDateTime));
    }

    /**
     * Parses the details of an event command and returns the corresponding Command object.
     *
     * @param details The details of the event command, including the task description and event period.
     * @return The corresponding Command object for the event.
     * @throws ScheduloException If the input is invalid or the date format is incorrect.
     */
    private static Command parseEventCommand(String details) throws ScheduloException {
        String[] taskParts = details.split(" /from ", 2);
        validateTaskParts(taskParts, "The description or /from of an event cannot be empty.");

        String[] deadlines = taskParts[1].split(" /to ", 2);
        validateEventParts(deadlines);

        String[] fromParts = deadlines[0].split(" ");
        String[] toParts = deadlines[1].split(" ");

        return createEventCommand(taskParts[0], fromParts, toParts);
    }

    private static void validateEventParts(String[] eventParts) throws ScheduloException {
        if (eventParts.length < 2) {
            throw new ScheduloException("The /from or /to of an event cannot be empty.");
        }
    }

    private static Command createEventCommand(String description, String[] fromParts, String[] toParts) throws ScheduloException {
        try {
            if (fromParts.length == 1 && toParts.length == 1) {
                return createEventWithDates(description, fromParts[0], toParts[0]);
            } else if (fromParts.length == 2 && toParts.length == 2) {
                return createEventWithDateTimes(description, fromParts[0], fromParts[1], toParts[0], toParts[1]);
            } else if (fromParts.length == 1 && toParts.length == 2) {
                return createEventWithMixedDateTime(description, fromParts[0], toParts[0], toParts[1], true);
            } else if (fromParts.length == 2 && toParts.length == 1) {
                return createEventWithMixedDateTime(description, fromParts[0], fromParts[1], toParts[0], false);
            } else {
                throw new ScheduloException("The /from and /to of an event should be in the format yyyy-MM-dd or yyyy-MM-dd HH:mm.");
            }
        } catch (DateTimeParseException e) {
            throw new ScheduloException("Invalid date format. Please use 'yyyy-MM-dd' for dates or 'yyyy-MM-dd HH:mm'.");
        }
    }

    private static Command createEventWithDates(String description, String fromDate, String toDate) throws ScheduloException {
        LocalDate from = LocalDate.parse(fromDate);
        LocalDate to = LocalDate.parse(toDate);
        if (from.isAfter(to)) {
            throw new ScheduloException("The start date of an event cannot be after the end date.");
        }
        return new AddCommand(new Event(description, from, to));
    }

    private static Command createEventWithDateTimes(String description, String fromDate, String fromTime, String toDate, String toTime) throws ScheduloException {
        LocalDateTime from = LocalDateTime.parse(fromDate + "T" + fromTime);
        LocalDateTime to = LocalDateTime.parse(toDate + "T" + toTime);
        if (from.isAfter(to)) {
            throw new ScheduloException("The start date of an event cannot be after the end date.");
        }
        return new AddCommand(new Event(description, from, to));
    }

    private static Command createEventWithMixedDateTime(String description, String date, String time, String otherDate, boolean isFromDate) throws ScheduloException {
        if (isFromDate) {
            LocalDate from = LocalDate.parse(date);
            LocalDateTime to = LocalDateTime.parse(otherDate + "T" + time);
            if (from.isAfter(to.toLocalDate())) {
                throw new ScheduloException("The start date of an event cannot be after the end date.");
            }
            return new AddCommand(new Event(description, from, to));
        } else {
            LocalDateTime from = LocalDateTime.parse(date + "T" + time);
            LocalDate to = LocalDate.parse(otherDate);
            if (from.toLocalDate().isAfter(to)) {
                throw new ScheduloException("The start date of an event cannot be after the end date.");
            }
            return new AddCommand(new Event(description, from, to));
        }
    }

    /**
     * Parses a task from a line in the storage file.
     *
     * @param taskLine A line from the storage file representing a task.
     * @return The corresponding Task object.
     */
    public static Task parseTask(String taskLine) {
        String[] taskParts = taskLine.split(",");
        String taskType = taskParts[0];
        boolean isDone = taskParts[1].equals("1");
        String taskDescription = taskParts[2];

        switch (taskType) {
        case "T":
            return new Todo(taskDescription, isDone);
        case "D":
            if (taskParts[3].length() == 10) {
                LocalDate deadlineDate = LocalDate.parse(taskParts[3]);
                return new Deadline(taskDescription, deadlineDate, isDone);
            } else {
                LocalDateTime deadlineDateTime = LocalDateTime.parse(taskParts[3].replace(" ", "T"));
                return new Deadline(taskDescription, deadlineDateTime, isDone);
            }
        case "E":
            if (taskParts[3].length() == 10 && taskParts[4].length() == 10) {
                LocalDate from = LocalDate.parse(taskParts[3]);
                LocalDate to = LocalDate.parse(taskParts[4]);
                return new Event(taskDescription, from, to, isDone);
            } else if (taskParts[3].length() != 10 && taskParts[4].length() != 10) {
                LocalDateTime from = LocalDateTime.parse(taskParts[3].replace(" ", "T"));
                LocalDateTime to = LocalDateTime.parse(taskParts[4].replace(" ", "T"));
                return new Event(taskDescription, from, to, isDone);
            } else if (taskParts[3].length() == 10 && taskParts[4].length() != 10) {
                LocalDate from = LocalDate.parse(taskParts[3]);
                LocalDateTime to = LocalDateTime.parse(taskParts[4].replace(" ", "T"));
                return new Event(taskDescription, from, to, isDone);
            } else {
                LocalDateTime from = LocalDateTime.parse(taskParts[3].replace(" ", "T"));
                LocalDate to = LocalDate.parse(taskParts[4]);
                return new Event(taskDescription, from, to, isDone);
            }
        default:
            return null;
        }
    }
}

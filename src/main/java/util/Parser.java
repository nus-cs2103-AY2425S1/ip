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
        if (taskParts.length < 2 || taskParts[0].trim().isEmpty()) {
            throw new ScheduloException("The description of a deadline or the /by cannot be empty.");
        }

        String[] dateParts = taskParts[1].split(" ");
        try {
            if (dateParts.length == 1) {
                LocalDate deadlineDate = LocalDate.parse(dateParts[0]);
                if (deadlineDate.isBefore(LocalDate.now())) {
                    throw new ScheduloException("The deadline cannot be in the past.");
                }
                return new AddCommand(new Deadline(taskParts[0], deadlineDate));
            } else if (dateParts.length == 2) {
                LocalDateTime deadlineDateTime = LocalDateTime.parse(dateParts[0] + "T" + dateParts[1]);
                if (deadlineDateTime.isBefore(LocalDateTime.now())) {
                    throw new ScheduloException("The deadline cannot be in the past.");
                }
                return new AddCommand(new Deadline(taskParts[0], deadlineDateTime));
            } else {
                throw new ScheduloException("The /by of a deadline should be in the format "
                        + "yyyy-MM-dd or yyyy-MM-dd HH:mm.");
            }
        } catch (DateTimeParseException e) {
            throw new ScheduloException("Invalid date format. Please use 'yyyy-MM-dd' for dates or "
                    + "'yyyy-MM-dd HH:mm' for date and time.");
        }
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
        if (taskParts.length < 2 || taskParts[0].trim().isEmpty()) {
            throw new ScheduloException("The description or /from of an event cannot be empty.");
        }

        String[] deadlines = taskParts[1].split(" /to ", 2);
        if (deadlines.length < 2) {
            throw new ScheduloException("The /from or /to of an event cannot be empty.");
        }

        String[] fromParts = deadlines[0].split(" ");
        String[] toParts = deadlines[1].split(" ");

        try {
            if (fromParts.length == 1 && toParts.length == 1) {
                LocalDate from = LocalDate.parse(fromParts[0]);
                LocalDate to = LocalDate.parse(toParts[0]);
                if (from.isAfter(to)) {
                    throw new ScheduloException("The start date of an event cannot be after the end date.");
                }
                return new AddCommand(new Event(taskParts[0], from, to));
            } else if (fromParts.length == 2 && toParts.length == 2) {
                LocalDateTime from = LocalDateTime.parse(fromParts[0] + "T" + fromParts[1]);
                LocalDateTime to = LocalDateTime.parse(toParts[0] + "T" + toParts[1]);
                if (from.isAfter(to)) {
                    throw new ScheduloException("The start date of an event cannot be after the end date.");
                }
                return new AddCommand(new Event(taskParts[0], from, to));
            } else if (fromParts.length == 1 && toParts.length == 2) {
                LocalDate from = LocalDate.parse(fromParts[0]);
                LocalDateTime to = LocalDateTime.parse(toParts[0] + "T" + toParts[1]);
                if (from.isAfter(to.toLocalDate())) {
                    throw new ScheduloException("The start date of an event cannot be after the end date.");
                }
                return new AddCommand(new Event(taskParts[0], from, to));
            } else if (fromParts.length == 2 && toParts.length == 1) {
                LocalDateTime from = LocalDateTime.parse(fromParts[0] + "T" + fromParts[1]);
                LocalDate to = LocalDate.parse(toParts[0]);
                if (from.toLocalDate().isAfter(to)) {
                    throw new ScheduloException("The start date of an event cannot be after the end date.");
                }
                return new AddCommand(new Event(taskParts[0], from, to));
            } else {
                throw new ScheduloException("The /from and /to of an event should be in the format yyyy-MM-dd or "
                        + "yyyy-MM-dd HH:mm.");
            }
        } catch (DateTimeParseException e) {
            throw new ScheduloException("Invalid date format. Please use 'yyyy-MM-dd' for dates or "
                    + "'yyyy-MM-dd HH:mm'.");
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

package delta.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import delta.command.AddCommand;
import delta.command.Command;
import delta.command.DeleteCommand;
import delta.command.EditCommand;
import delta.command.ExitCommand;
import delta.command.FindCommand;
import delta.command.MarkCommand;
import delta.command.PrintCommand;
import delta.command.UnmarkCommand;
import delta.exception.DeltaException;
import delta.task.Deadline;
import delta.task.Event;
import delta.task.Todo;

/**
 * Deals with making sense of user input.
 */
public class Parser {
    private static final String DATE_TIME_ERROR = """
            OOPS!!! The format used for date/time is wrong!
            \t Please follow the proper format:
            \t yyyy-MM-dd HHmm
            \t eg. 2024-08-25 1800""";
    private static final String FIND_ERROR = """
            OOPS!!! Description of task to find cannot be left blank!
            \t Please follow the proper format:
            \t * find [description]""";
    private static final String TODO_ERROR = """
            OOPS!!! The format for todo is wrong!
            \t Please follow the proper format:
            \t * todo [description]""";
    private static final String DEADLINE_ERROR = """
            OOPS!!! The format for deadline is wrong!
            \t Please follow the proper format:
            \t * deadline [description] /by [date/time]""";
    private static final String EVENT_ERROR = """
            OOPS!!! The format for event is wrong!
            \t Please follow the proper format:
            \t * event [description] /from [start] /to [end]""";
    private static final String PAST_TIME_ERROR = """
            OOPS!!! The date/time cannot be in the past!
            \t Please set to a future date/time!""";
    private static final String END_BEFORE_START_ERROR = """
            OOPS!!! The end date cannot be before the start date!
            \t Please set the correct date/time!""";
    private static final String MARK_ERROR = """
            OOPS!!! The format to mark tasks is wrong!
            \t Please follow the proper format:
            \t * mark [index of task]""";
    private static final String UNMARK_ERROR = """
            OOPS!!! The format to unmark tasks is wrong!
            \t Please follow the proper format:
            \t * unmark [index of task]""";
    private static final String DELETE_ERROR = """
            OOPS!!! The format to delete tasks is wrong!
            \t Please follow the proper format:
            \t * delete [index of task]""";
    private static final String EDIT_ERROR = """
            OOPS!!! The format to edit tasks is wrong!
            \t Please follow the proper format:
            \t * edit [index of task] [task attribute] [new value]
            \t Possible task attributes include:
            \t * Todo: /desc
            \t * Deadline: /desc /by
            \t * Event: /desc /from /to""";
    private static final String INDEX_ERROR = """
            OOPS!!! The index of a task must be an integer!
            \t Please input a valid index!""";
    private static final String UNKNOWN_ERROR = """
            OOPS!!! I'm sorry, but I don't know what that means :-(
               Please follow the proper formats:
               * todo [description]
               * deadline [description] /by [date/time]
               * event [description] /from [start] /to [end]
               * mark [index of task]
               * unmark [index of task]
               * delete [index of task]
               * edit [index of task] [task attribute] [new value]""";

    /**
     * Formats a user typed date/time into proper format to be used by system.
     *
     * @param input User typed date/time.
     * @return LocalDateTime object containing date/time obtained from input.
     * @throws DeltaException If date/time input from user given in the wrong format.
     */
    private static LocalDateTime formatDateTime(String input) throws DeltaException {
        try {
            // Format to be provided by user input
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            throw new DeltaException(DATE_TIME_ERROR);
        }
    }

    /**
     * Returns ExitCommand to exit ChatBot.
     */
    private static Command sayBye() {
        return new ExitCommand();
    }

    /**
     * Returns PrintCommand to print all tasks in TaskList.
     */
    private static Command printTasks() {
        return new PrintCommand();
    }

    /**
     * Returns FindCommand to look for task in TaskList.
     *
     * @param description Description of command to be executed.
     * @throws DeltaException If command not given in correct format.
     */
    private static Command findTask(String[] description) throws DeltaException {
        // Only task given, no description
        if (description.length != 2) {
            throw new DeltaException(FIND_ERROR);
        }

        String taskName = description[1].strip();
        return new FindCommand(taskName);
    }

    /**
     * Returns AddCommand to add Todo in TaskList.
     *
     * @param description Description of command to be executed.
     * @throws DeltaException If command not given in correct format.
     */
    private static Command addTodo(String[] description) throws DeltaException {
        // Only task given, no description
        if (description.length != 2) {
            throw new DeltaException(TODO_ERROR);
        }

        String todoName = description[1].strip();
        return new AddCommand(new Todo(todoName));
    }

    /**
     * Returns AddCommand to add Deadline in TaskList.
     *
     * @param description Description of command to be executed.
     * @throws DeltaException If command not given in correct format.
     */
    private static Command addDeadline(String[] description) throws DeltaException {
        String deadlineName;
        LocalDateTime by;

        try {
            // Split task name and deadline
            String[] deadlineDetails = description[1].strip().split(" /by ");

            deadlineName = deadlineDetails[0].strip();
            by = formatDateTime(deadlineDetails[1].strip());
        } catch (IndexOutOfBoundsException e) {
            throw new DeltaException(DEADLINE_ERROR);
        }

        // Deadline is in the past
        if (by.isBefore(LocalDateTime.now())) {
            throw new DeltaException(PAST_TIME_ERROR);
        }

        return new AddCommand(new Deadline(deadlineName, by));
    }

    /**
     * Returns AddCommand to add Event in TaskList.
     *
     * @param description Description of command to be executed.
     * @throws DeltaException If command not given in correct format.
     */
    private static Command addEvent(String[] description) throws DeltaException {
        String eventName;
        LocalDateTime start;
        LocalDateTime end;

        try {
            // Split task name and timing details
            String[] eventDetails = description[1].strip().split(" /from ");

            eventName = eventDetails[0].strip();

            // Split start time and end time
            String[] timings = eventDetails[1].strip().split(" /to ");

            start = formatDateTime(timings[0].strip());
            end = formatDateTime(timings[1].strip());
        } catch (IndexOutOfBoundsException e) {
            throw new DeltaException(EVENT_ERROR);
        }

        // Timings are in the past
        if (start.isBefore(LocalDateTime.now()) || end.isBefore(LocalDateTime.now())) {
            throw new DeltaException(PAST_TIME_ERROR);
        }

        // End time is before start time
        if (end.isBefore(start)) {
            throw new DeltaException(END_BEFORE_START_ERROR);
        }

        return new AddCommand(new Event(eventName, start, end));
    }

    /**
     * Returns MarkCommand to mark task in TaskList.
     *
     * @param description Description of command to be executed.
     * @throws DeltaException If command not given in correct format.
     */
    private static Command markTask(String[] description) throws DeltaException {
        int markTaskIdx;

        try {
            markTaskIdx = Integer.parseInt(description[1].strip());
        } catch (IndexOutOfBoundsException e) {
            throw new DeltaException(MARK_ERROR);
        } catch (NumberFormatException e) {
            throw new DeltaException(INDEX_ERROR);
        }

        return new MarkCommand(markTaskIdx);
    }

    /**
     * Returns UnmarkCommand to unmark task in TaskList.
     *
     * @param description Description of command to be executed.
     * @throws DeltaException If command not given in correct format.
     */
    private static Command unmarkTask(String[] description) throws DeltaException {
        int unmarkTaskIdx;

        try {
            unmarkTaskIdx = Integer.parseInt(description[1].strip());
        } catch (IndexOutOfBoundsException e) {
            throw new DeltaException(UNMARK_ERROR);
        } catch (NumberFormatException e) {
            throw new DeltaException(INDEX_ERROR);
        }

        return new UnmarkCommand(unmarkTaskIdx);
    }

    /**
     * Returns DeleteCommand to delete task in TaskList.
     *
     * @param description Description of command to be executed.
     * @throws DeltaException If command not given in correct format.
     */
    private static Command deleteTask(String[] description) throws DeltaException {
        int deleteTaskIdx;

        try {
            deleteTaskIdx = Integer.parseInt(description[1].strip());
        } catch (IndexOutOfBoundsException e) {
            throw new DeltaException(DELETE_ERROR);
        } catch (NumberFormatException e) {
            throw new DeltaException(INDEX_ERROR);
        }

        return new DeleteCommand(deleteTaskIdx);
    }

    private static Command editTask(String[] description) throws DeltaException {
        int editTaskIdx;
        String editType;
        String editDesc = null;
        LocalDateTime editTime = null;

        try {
            // Split task index and edit details
            String[] editDetails = description[1].strip().split(" ", 3);

            editTaskIdx = Integer.parseInt(editDetails[0].strip());
            editType = editDetails[1].strip();

            if (editType.equals("/desc")) {
                editDesc = editDetails[2].strip();
            } else if (editType.equals("/by") || editType.equals("/from") || editType.equals("/to")){
                editTime = formatDateTime(editDetails[2].strip());
                if (editTime.isBefore(LocalDateTime.now())) {
                    throw new DeltaException(PAST_TIME_ERROR);
                }
            } else {
                throw new DeltaException(EDIT_ERROR);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DeltaException(EDIT_ERROR);
        } catch (NumberFormatException e) {
            throw new DeltaException(INDEX_ERROR);
        }

        return new EditCommand(editTaskIdx, editType, editDesc, editTime);
    }

    /**
     * Parses user input into a command understood by the system.
     *
     * @param input User typed command.
     * @return Command to be used by system to execute an action.
     * @throws DeltaException If format of user input is wrong.
     */
    public static Command parse(String input) throws DeltaException {
        String[] description = input.strip().split(" ", 2);
        String task = description[0];

        return switch (task.toLowerCase()) {
        // Bye
        case "bye" -> sayBye();

        // Print List
        case "list" -> printTasks();

        // Find Tasks
        case "find" -> findTask(description);

        // Add Todo
        case "todo" -> addTodo(description);

        // Add Deadline
        case "deadline" -> addDeadline(description);

        // Add Event
        case "event" -> addEvent(description);

        // Mark Task
        case "mark" -> markTask(description);

        // Unmark Task
        case "unmark" -> unmarkTask(description);

        // Delete Task
        case "delete" -> deleteTask(description);

        // Edit Task
        case "edit" -> editTask(description);

        // Unknown Action
        default -> throw new DeltaException(UNKNOWN_ERROR);
        };
    }
}

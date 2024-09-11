package delta.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import delta.command.AddCommand;
import delta.command.Command;
import delta.command.DeleteCommand;
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
               * delete [index of task]""";

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
     * Parses user input into a command understood by the system.
     *
     * @param input User typed command.
     * @return Command to be used by system to execute an action.
     * @throws DeltaException If format of user input is wrong.
     */
    public static Command parse(String input) throws DeltaException {
        String[] description = input.strip().split(" ", 2);
        String task = description[0];

        // Bye
        if (task.equalsIgnoreCase("bye")) {
            return new ExitCommand();

        // Print List
        } else if (task.equalsIgnoreCase("list")) {
            return new PrintCommand();

        // Find Tasks
        } else if (task.equalsIgnoreCase("find")) {
            if (description.length == 2) {
                String taskName = description[1].strip();
                return new FindCommand(taskName);
            } else {
                throw new DeltaException(FIND_ERROR);
            }

        // Add Todo
        } else if (task.equalsIgnoreCase("todo")) {
            if (description.length == 2) {
                String taskName = description[1].strip();
                return new AddCommand(new Todo(taskName));
            // Only task given, no description
            } else {
                throw new DeltaException(TODO_ERROR);
            }

        // Add Deadline
        } else if (task.equalsIgnoreCase("deadline")) {
            if (description.length == 2) {
                // Split task name and deadline
                String[] details = description[1].strip().split(" /by ");
                if (details.length == 2) {
                    String taskName = details[0].strip();
                    LocalDateTime by = formatDateTime(details[1].strip());
                    // Check if deadline is in the past
                    if (by.isBefore(LocalDateTime.now())) {
                        throw new DeltaException(PAST_TIME_ERROR);
                    }
                    return new AddCommand(new Deadline(taskName, by));
                // More than one deadline given
                } else {
                    throw new DeltaException(DEADLINE_ERROR);
                }
            // Only task given, no description
            } else {
                throw new DeltaException(DEADLINE_ERROR);
            }

        // Add Event
        } else if (task.equalsIgnoreCase("event")) {
            if (description.length == 2) {
                // Split task name and timing details
                String[] details = description[1].strip().split(" /from ");
                if (details.length == 2) {
                    String taskName = details[0].strip();
                    // Split start time and end time
                    String[] timings = details[1].strip().split(" /to ");
                    if (timings.length == 2) {
                        LocalDateTime start = formatDateTime(timings[0].strip());
                        LocalDateTime end = formatDateTime(timings[1].strip());
                        // Check if timings are in the past
                        if (start.isBefore(LocalDateTime.now()) || end.isBefore(LocalDateTime.now())) {
                            throw new DeltaException(PAST_TIME_ERROR);
                        // Check if end time is before start time
                        } else if (end.isBefore(start)) {
                            throw new DeltaException(END_BEFORE_START_ERROR);
                        }
                        return new AddCommand(new Event(taskName, start, end));
                    // More than one end time given
                    } else {
                        throw new DeltaException(EVENT_ERROR);
                    }
                // More than one start time given
                } else {
                    throw new DeltaException(EVENT_ERROR);
                }
            // Only task given, no description
            } else {
                throw new DeltaException(EVENT_ERROR);
            }

        // Mark Task
        } else if (task.equalsIgnoreCase("mark")) {
            // Only task given, no description
            if (description.length != 2) {
                throw new DeltaException(MARK_ERROR);
            }
            try {
                int taskIdx = Integer.parseInt(description[1].strip());
                return new MarkCommand(taskIdx);
            // Index given not an integer
            } catch (NumberFormatException e) {
                throw new DeltaException(INDEX_ERROR);
            }

        // Unmark Task
        } else if (task.equalsIgnoreCase("unmark")) {
            // Only task given, no description
            if (description.length != 2) {
                throw new DeltaException(UNMARK_ERROR);
            }
            try {
                int taskIdx = Integer.parseInt(description[1].strip());
                return new UnmarkCommand(taskIdx);
            // Index given not an integer
            } catch (NumberFormatException e) {
                throw new DeltaException(INDEX_ERROR);
            }

        // Delete Task
        } else if (task.equalsIgnoreCase("delete")) {
            // Only task given, no description
            if (description.length != 2) {
                throw new DeltaException(DELETE_ERROR);
            }
            try {
                int taskIdx = Integer.parseInt(description[1].strip());
                return new DeleteCommand(taskIdx);
            // Index given is not an integer
            } catch (NumberFormatException e) {
                throw new DeltaException(INDEX_ERROR);
            }

        // Unknown Action
        } else {
            throw new DeltaException(UNKNOWN_ERROR);
        }
    }
}

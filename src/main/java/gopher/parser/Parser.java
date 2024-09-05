package gopher.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import gopher.exception.EmptyTaskDescriptionException;
import gopher.exception.MissingTokenException;
import gopher.exception.UnknownCommandException;
import gopher.task.Deadline;
import gopher.task.Event;
import gopher.task.Task;
import gopher.task.ToDo;

/**
 * Groups the logic and functions for parsing input, command and data from
 * user and data files.
 */
public class Parser {
    /**
     * DateTimeFormater for date input
     */
    private static final DateTimeFormatter dateInputFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * DateTimeFormatter for date display
     */
    private static final DateTimeFormatter dateTextFormatter =
            DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Parses the date input into LocalDateTime Object.
     *
     * @param input date input from the user or file
     * @return LocalDateTime object of the given date
     */
    public static LocalDateTime parseDateString(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException();
        }
        String[] tokens = input.split(" ");
        String date = tokens[0];
        String time = tokens.length == 2 ? tokens[1] : "";

        return LocalDateTime.parse(date
                        + " "
                        + (time.isEmpty() ? "00:00" : time),
                dateInputFormatter);
    }

    /**
     * Parses the LocalDateTime object to date input.
     *
     * @param date LocalDateTime Object to be parsed
     * @return date string in input form(yyyy-MM-dd HH:mm)
     */
    public static String parseLocalDateTime(LocalDateTime date) {
        return date.format(dateInputFormatter);
    }

    /**
     * Displays the Date represented by LocalDateTime Object.
     *
     * @param date LocalDateTime object whose date needs to be displayed
     * @return date string in display form(MMM dd yyyy HH:mm)
     */
    public static String displayDate(LocalDateTime date) {
        return date.format(dateTextFormatter);
    }

    /**
     * Parses create task command.
     *
     * @param command string input from user or file
     * @return task of the correct type with relevant input details
     */
    public static Task parseCreateTaskCommand(String command)
            throws UnknownCommandException, DateTimeParseException,
            EmptyTaskDescriptionException, MissingTokenException {
        String[] tokens = command.split(" ");

        String taskType = tokens[0];
        if (!taskType.equalsIgnoreCase("todo")
                && !taskType.equalsIgnoreCase("deadline")
                && !taskType.equalsIgnoreCase("event")) {
            throw new UnknownCommandException(taskType);
        }

        if (tokens.length < 2) {
            throw new EmptyTaskDescriptionException(taskType);
        }

        StringBuilder taskName = new StringBuilder();
        if (taskType.equalsIgnoreCase("todo")) {
            for (int i = 1; i < tokens.length; i++) {
                taskName.append(tokens[i]);
                if (i < tokens.length - 1) {
                    taskName.append(" ");
                }
            }
            return new ToDo(taskName.toString());
        } else if (taskType.equalsIgnoreCase("deadline")) {
            int byTokenIndex = -1;
            for (int i = 1; i < tokens.length; i++) {
                if (tokens[i].equalsIgnoreCase("/by")) {
                    byTokenIndex = i;
                }
            }

            if (byTokenIndex == -1) {
                throw new MissingTokenException(taskType, "/by");
            }

            for (int i = 1; i < byTokenIndex; i++) {
                taskName.append(tokens[i]);
                if (i < byTokenIndex - 1) {
                    taskName.append(" ");
                }
            }

            StringBuilder dueDate = new StringBuilder();
            for (int i = byTokenIndex + 1; i < tokens.length; i++) {
                dueDate.append(tokens[i]);
                if (i < tokens.length - 1) {
                    dueDate.append(" ");
                }
            }

            return new Deadline(taskName.toString(), dueDate.toString());
        } else if (taskType.equalsIgnoreCase("event")) {
            int fromTokenIndex = -1;
            int toTokenIndex = -1;
            for (int i = 1; i < tokens.length; i++) {
                if (tokens[i].equalsIgnoreCase("/from")) {
                    fromTokenIndex = i;
                }
                if (tokens[i].equalsIgnoreCase("/to")) {
                    toTokenIndex = i;
                }
            }

            if (fromTokenIndex == -1) {
                throw new MissingTokenException(taskType, "/from");
            }

            if (toTokenIndex == -1) {
                throw new MissingTokenException(taskType, "/to");
            }

            for (int i = 1; i < fromTokenIndex; i++) {
                taskName.append(tokens[i]);
                if (i < fromTokenIndex - 1) {
                    taskName.append(" ");
                }
            }

            StringBuilder startDate = new StringBuilder();
            for (int i = fromTokenIndex + 1; i < toTokenIndex; i++) {
                startDate.append(tokens[i]);
                if (i < toTokenIndex - 1) {
                    startDate.append(" ");
                }
            }

            StringBuilder endDate = new StringBuilder();
            for (int i = toTokenIndex + 1; i < tokens.length; i++) {
                endDate.append(tokens[i]);
                if (i < tokens.length - 1) {
                    endDate.append(" ");
                }
            }
            return new Event(taskName.toString(),
                    startDate.toString(),
                    endDate.toString());
        } else {
            throw new UnknownCommandException(taskType);
        }
    }

    /**
     * Parses mark task as done command.
     *
     * @param command mark task command
     * @return task number of the task marked as done
     */
    public static int[] parseMarkCommand(String command) {
        String[] tokens = command.split(" ");
        int[] taskNumbers = new int[tokens.length - 1];
        for (int i = 1; i < tokens.length; i++) {
            taskNumbers[i - 1] = Integer.parseInt(tokens[i]);
        }
        return taskNumbers;
    }

    /**
     * Parses mark task as not done command.
     *
     * @param command unmark task command
     * @return task number of the task marked as not done
     */
    public static int[] parseUnmarkCommand(String command) {
        String[] tokens = command.split(" ");
        int[] taskNumbers = new int[tokens.length - 1];
        for (int i = 1; i < tokens.length; i++) {
            taskNumbers[i - 1] = Integer.parseInt(tokens[i]);
        }
        return taskNumbers;
    }

    /**
     * Parses delete task command.
     *
     * @param command delete task command
     * @return task number of the task to be deleted
     */
    public static int[] parseDeleteCommand(String command) {
        String[] tokens = command.split(" ");
        int[] taskNumbers = new int[tokens.length - 1];
        for (int i = 1; i < tokens.length; i++) {
            taskNumbers[i - 1] = Integer.parseInt(tokens[i]);
        }
        return taskNumbers;
    }

    /**
     * Parses find task command.
     *
     * @param command find task command
     * @return keyword used for searching
     */
    public static String parseFindCommand(String command) {
        String[] tokens = command.split(" ");
        StringBuilder keyword = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
            keyword.append(tokens[i]);
            if (i < tokens.length - 1) {
                keyword.append(" ");
            }
        }
        return keyword.toString();
    }
}

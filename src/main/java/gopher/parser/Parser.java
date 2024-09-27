package gopher.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import gopher.exception.EmptyTaskDescriptionException;
import gopher.exception.FileCorruptedException;
import gopher.exception.InvalidDurationException;
import gopher.exception.InvalidTokenException;
import gopher.exception.MissingTaskNumberException;
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
    private static final DateTimeFormatter DATE_INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * DateTimeFormatter for date display
     */
    private static final DateTimeFormatter DATE_TEXT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Parses the date input into LocalDateTime Object.
     *
     * @param input date input from the user or file(yyyy-MM-dd HH:mm)
     * @return LocalDateTime object of the given date
     */
    public static LocalDateTime parseDateString(String input) {
        String[] tokens = input.split(" ");
        String date = tokens[0];
        String time = tokens.length == 2 ? tokens[1] : "00:00";

        return LocalDateTime.parse(date + " " + time,
                DATE_INPUT_FORMAT);
    }

    /**
     * Parses the LocalDateTime object to date input.
     *
     * @param date LocalDateTime Object to be parsed
     * @return date string in input form(yyyy-MM-dd HH:mm)
     */
    public static String parseLocalDateTime(LocalDateTime date) {
        assert date != null : "LocalDateTime object being saved cannot be null";
        return date.format(DATE_INPUT_FORMAT);
    }

    /**
     * Displays the Date represented by LocalDateTime Object on the UI.
     *
     * @param date LocalDateTime object whose date needs to be displayed
     * @return date string in display form(MMM dd yyyy HH:mm)
     */
    public static String displayDate(LocalDateTime date) {
        assert date != null : "LocalDateTime object being displayed cannot be null";
        return date.format(DATE_TEXT_FORMAT);
    }

    /**
     * Checks if a given task type is valid or not.
     *
     * @param type String representing the task type in the command
     * @return whether the given task type is valid or not
     */
    public static boolean isValidTaskType(String type) {
        return type.equalsIgnoreCase("todo")
                || type.equalsIgnoreCase("deadline")
                || type.equalsIgnoreCase("event");
    }

    /**
     * Checks for whether at least one task number is supplied in the given command.
     */
    public static void hasTaskNumber(int[] taskNumbers)
            throws MissingTaskNumberException {
        if (taskNumbers.length == 0) {
            throw new MissingTaskNumberException();
        }
    }

    /**
     * Parses a todo task creation command.
     *
     * @param tokens tokens within the given command
     * @return ToDo task with the correct detail
     */
    public static Task parseCreateToDoCommand(String[] tokens)
            throws InvalidTokenException {
        // Form task name based on the given tokens
        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].startsWith("/")) {
                throw new InvalidTokenException("todo", tokens[i]);
            }
            taskName.append(tokens[i]);
            if (i < tokens.length - 1) {
                taskName.append(" ");
            }
        }
        return new ToDo(taskName.toString());
    }

    /**
     * Parses a deadline task creation command.
     *
     * @param tokens tokens within the given command
     * @return Deadline task with the correct detail
     */
    public static Task parseCreateDeadlineCommand(String[] tokens)
            throws MissingTokenException, InvalidTokenException {
        // Index to keep track of the exact position of the command tokens
        int byTokenIndex = -1;

        // Determine position of /by token and raise error if it is missing
        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].equalsIgnoreCase("/by")) {
                byTokenIndex = i;
            } else if (tokens[i].startsWith("/")) {
                throw new InvalidTokenException("deadline", tokens[i]);
            }
        }
        if (byTokenIndex == -1) {
            throw new MissingTokenException("deadline", "/by");
        }

        // Form task name from the token
        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < byTokenIndex; i++) {
            taskName.append(tokens[i]);
            if (i < byTokenIndex - 1) {
                taskName.append(" ");
            }
        }

        // Form due date from the token
        StringBuilder dueDate = new StringBuilder();
        for (int i = byTokenIndex + 1; i < tokens.length; i++) {
            dueDate.append(tokens[i]);
            if (i < tokens.length - 1) {
                dueDate.append(" ");
            }
        }

        return new Deadline(taskName.toString(), dueDate.toString());
    }

    /**
     * Parses an event task creation command.
     *
     * @param tokens tokens within the given command
     * @return Event task with the correct detail
     */
    public static Task parseCreateEventCommand(String[] tokens)
            throws MissingTokenException, InvalidTokenException,
            InvalidDurationException {
        // Indexes to track the position of command tokens
        int fromTokenIndex = -1;
        int toTokenIndex = -1;

        // Determine the position of the tokens and raise error if any of them is missing
        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].equalsIgnoreCase("/from")) {
                fromTokenIndex = i;
            } else if (tokens[i].equalsIgnoreCase("/to")) {
                toTokenIndex = i;
            } else if (tokens[i].startsWith("/")) {
                throw new InvalidTokenException("event", tokens[i]);
            }
        }
        if (fromTokenIndex == -1) {
            throw new MissingTokenException("event", "/from");
        }
        if (toTokenIndex == -1) {
            throw new MissingTokenException("event", "/to");
        }

        // Form task name from the tokens
        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < fromTokenIndex; i++) {
            taskName.append(tokens[i]);
            if (i < fromTokenIndex - 1) {
                taskName.append(" ");
            }
        }

        // Form start date from the tokens
        StringBuilder startDate = new StringBuilder();
        for (int i = fromTokenIndex + 1; i < toTokenIndex; i++) {
            startDate.append(tokens[i]);
            if (i < toTokenIndex - 1) {
                startDate.append(" ");
            }
        }

        // Form end date from the tokens
        StringBuilder endDate = new StringBuilder();
        for (int i = toTokenIndex + 1; i < tokens.length; i++) {
            endDate.append(tokens[i]);
            if (i < tokens.length - 1) {
                endDate.append(" ");
            }
        }

        LocalDateTime start = parseDateString(startDate.toString());
        LocalDateTime end = parseDateString(endDate.toString());
        if (end.isBefore(start)) {
            throw new InvalidDurationException();
        }

        return new Event(taskName.toString(),
                startDate.toString(),
                endDate.toString());
    }

    /**
     * Parses create task command.
     *
     * @param command string input from user or file
     * @return task of the correct type with relevant input details
     */
    public static Task parseCreateTaskCommand(String command)
            throws UnknownCommandException, DateTimeParseException,
            EmptyTaskDescriptionException, MissingTokenException,
            InvalidTokenException, InvalidDurationException {
        String[] tokens = command.split(" ");

        String taskType = tokens[0];
        if (!isValidTaskType(taskType)) {
            throw new UnknownCommandException(taskType);
        }

        if (tokens.length < 2) {
            throw new EmptyTaskDescriptionException(taskType);
        }

        if (taskType.equalsIgnoreCase("todo")) {
            return parseCreateToDoCommand(tokens);
        } else if (taskType.equalsIgnoreCase("deadline")) {
            return parseCreateDeadlineCommand(tokens);
        } else if (taskType.equalsIgnoreCase("event")) {
            return parseCreateEventCommand(tokens);
        } else {
            throw new UnknownCommandException(taskType);
        }
    }

    /**
     * Parses update todo task command.
     *
     * @param tokens tokens within the given update command
     * @return new name to be updated
     */
    public static String[] parseUpdateTodoTaskCommand(String[] tokens)
            throws InvalidTokenException {
        StringBuilder taskName = new StringBuilder();
        String[] result = new String[1];
        for (int i = 2; i < tokens.length; i++) {
            // If other tasks tokens are used, remind user that
            // he/she may be updating the undesired task
            if (tokens[i].startsWith("/")) {
                throw new InvalidTokenException("todo", tokens[i]);
            }

            // Append the token if everything is fine
            taskName.append(tokens[i]);
            if (i < tokens.length - 1) {
                taskName.append(" ");
            }
        }
        result[0] = taskName.toString();
        return result;
    }

    /**
     * Parses update deadline task command.
     *
     * @param tokens tokens within the given command
     * @return a String array of size 2, the first element is the task name,
     *         the second element is the due date in date input format
     *         If a certain field is not provided in the given command, the
     *         corresponding element in the array will be empty String
     */
    public static String[] parseUpdateDeadlineTaskCommand(String[] tokens)
            throws InvalidTokenException {
        String[] result = new String[]{"", ""};
        // Determine the position of /by token in update command tokens
        // Check for any invalid tokens as well
        int byTokenIndex = -1;
        for (int i = 2; i < tokens.length; i++) {
            if (tokens[i].equalsIgnoreCase("/by")) {
                byTokenIndex = i;
            } else if (tokens[i].startsWith("/")) {
                throw new InvalidTokenException("deadline", tokens[i]);
            }
        }

        // Update task name based on the position of /by token
        StringBuilder taskName = new StringBuilder();
        int taskNameIndexLimit = byTokenIndex != -1 ? byTokenIndex : tokens.length;
        for (int i = 2; i < taskNameIndexLimit; i++) {
            taskName.append(tokens[i]);
            if (i < taskNameIndexLimit - 1) {
                taskName.append(" ");
            }
        }
        result[0] = taskName.toString();

        // Update due date if /by token is found in command tokens
        if (byTokenIndex != -1) {
            StringBuilder dueDateString = new StringBuilder();
            for (int i = byTokenIndex + 1; i < tokens.length; i++) {
                dueDateString.append(tokens[i]);
                if (i < tokens.length - 1) {
                    dueDateString.append(" ");
                }
            }
            result[1] = dueDateString.toString();
        }

        return result;
    }

    /**
     * Parses update event task command.
     *
     * @param tokens tokens within the given update command
     * @return a String array of size 3, the first element is the task name,
     *         the second element is the start date in date input format,
     *         the third element is the end date in date input format
     *         If the update command does not contain a certain part, the
     *         corresponding element would be empty String
     */
    public static String[] parseUpdateEventTaskCommand(String[] tokens)
            throws InvalidTokenException {
        String[] result = new String[]{"", "", ""};

        // Determine the positions of from & to tokens if they exist in the command tokens
        // Check for any invalid token as well
        int fromTokenIndex = -1;
        int toTokenIndex = -1;
        for (int i = 2; i < tokens.length; i++) {
            if (tokens[i].equalsIgnoreCase("/from")) {
                fromTokenIndex = i;
            } else if (tokens[i].equalsIgnoreCase("/to")) {
                toTokenIndex = i;
            } else if (tokens[i].startsWith("/")) {
                throw new InvalidTokenException("event", tokens[i]);
            }
        }

        // Update task name based on the position of /from and /to token
        StringBuilder taskName = new StringBuilder();
        int taskNameIndexLimit = fromTokenIndex != -1
                ? fromTokenIndex
                : toTokenIndex != -1
                ? toTokenIndex
                : tokens.length;
        for (int i = 2; i < taskNameIndexLimit; i++) {
            taskName.append(tokens[i]);
            if (i < taskNameIndexLimit - 1) {
                taskName.append(" ");
            }
        }
        result[0] = taskName.toString();

        // Update start date if /from token exists in the command tokens
        if (fromTokenIndex != -1) {
            StringBuilder startDateString = new StringBuilder();
            int startDateIndexLimit = toTokenIndex != -1
                    ? toTokenIndex
                    : tokens.length;
            for (int i = taskNameIndexLimit + 1; i < startDateIndexLimit; i++) {
                startDateString.append(tokens[i]);
                if (i < startDateIndexLimit - 1) {
                    startDateString.append(" ");
                }
            }
            result[1] = startDateString.toString();
        }

        // Update end date if /to token exists in the command tokens
        if (toTokenIndex != -1) {
            StringBuilder endDateString = new StringBuilder();
            for (int i = toTokenIndex + 1; i < tokens.length; i++) {
                endDateString.append(tokens[i]);
                if (i < tokens.length - 1) {
                    endDateString.append(" ");
                }
            }
            result[2] = endDateString.toString();
        }

        return result;
    }

    /**
     * Parses saved task data to ArrayList of Task
     *
     * @param taskData task data within the saved task file
     * @return ArrayList of tasks represented by the save file
     * @throws FileCorruptedException if file read failed
     */
    public static ArrayList<Task> parseSavedTaskData(String taskData)
            throws FileCorruptedException {
        ArrayList<Task> tasks = new ArrayList<>();
        String[] taskRows = taskData.split("\n");
        String[] tokens;
        for (String row : taskRows) {
            if (row.isEmpty()) {
                continue;
            }
            tokens = row.split(" \\| ");
            String taskType = tokens[0];
            String taskStatus = tokens[1];
            String taskName = tokens[2];
            try {
                Task newTask = null;
                switch (taskType) {
                case "T":
                    newTask = Task.of("todo " + taskName);
                    break;
                case "D":
                    newTask = Task.of(String.format("deadline %s /by %s",
                            taskName,
                            tokens[3]));
                    break;
                case "E":
                    newTask = Task.of(String.format("event %s /from %s /to %s",
                            taskName,
                            tokens[3],
                            tokens[4]));
                    break;
                default:
                    break;
                }
                if (newTask != null && taskStatus.equals("X")) {
                    newTask.markAsDone();
                }
                tasks.add(newTask);
            } catch (UnknownCommandException
                     | EmptyTaskDescriptionException
                     | MissingTokenException
                     | InvalidTokenException
                     | InvalidDurationException e) {
                throw new FileCorruptedException();
            }
        }
        return tasks;
    }

    /**
    * Parses mark task as done command.
    *
    * @param command mark task command
    * @return task number of the task marked as done
    */
    public static int[] parseMarkCommand(String command)
            throws MissingTaskNumberException {
        try {
            String[] tokens = command.split(" ");
            int[] taskNumbers = new int[tokens.length - 1];
            for (int i = 1; i < tokens.length; i++) {
                taskNumbers[i - 1] = Integer.parseInt(tokens[i]);
            }
            hasTaskNumber(taskNumbers);
            return taskNumbers;
        } catch (NumberFormatException e) {
            throw new MissingTaskNumberException();
        }
    }

    /**
     * Parses mark task as not done command.
     *
     * @param command unmark task command
     * @return task number of the task marked as not done
     */
    public static int[] parseUnmarkCommand(String command)
            throws MissingTaskNumberException {
        try {
            String[] tokens = command.split(" ");
            int[] taskNumbers = new int[tokens.length - 1];
            for (int i = 1; i < tokens.length; i++) {
                taskNumbers[i - 1] = Integer.parseInt(tokens[i]);
            }
            hasTaskNumber(taskNumbers);
            return taskNumbers;
        } catch (NumberFormatException e) {
            throw new MissingTaskNumberException();
        }
    }

    /**
     * Parses delete task command.
     *
     * @param command delete task command
     * @return task number of the task to be deleted
     */
    public static int[] parseDeleteCommand(String command)
            throws MissingTaskNumberException {
        try {
            String[] tokens = command.split(" ");
            int[] taskNumbers = new int[tokens.length - 1];
            for (int i = 1; i < tokens.length; i++) {
                taskNumbers[i - 1] = Integer.parseInt(tokens[i]);
            }
            hasTaskNumber(taskNumbers);
            return taskNumbers;
        } catch (NumberFormatException e) {
            throw new MissingTaskNumberException();
        }
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

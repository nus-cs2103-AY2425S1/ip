package gopher.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import gopher.exception.EmptyTaskDescriptionException;
import gopher.exception.FileCorruptedException;
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
        if (input.isEmpty()) {
            throw new IllegalArgumentException();
        }
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
        return date.format(DATE_INPUT_FORMAT);
    }

    /**
     * Displays the Date represented by LocalDateTime Object on the UI.
     *
     * @param date LocalDateTime object whose date needs to be displayed
     * @return date string in display form(MMM dd yyyy HH:mm)
     */
    public static String displayDate(LocalDateTime date) {
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
     * Parses a todo task creation command.
     *
     * @param tokens tokens within the given command
     * @return ToDo task with the correct detail
     */
    public static Task parseCreateToDoCommand(String[] tokens) {
        // Form task name based on the given tokens
        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
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
            throws MissingTokenException {
        // Index to keep track of the exact position of the command tokens
        int byTokenIndex = -1;

        // Determine position of /by token and raise error if it is missing
        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].equalsIgnoreCase("/by")) {
                byTokenIndex = i;
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
            throws MissingTokenException {
        // Indexes to track the position of command tokens
        int fromTokenIndex = -1;
        int toTokenIndex = -1;

        // Determine the position of the tokens and raise error if any of them is missing
        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].equalsIgnoreCase("/from")) {
                fromTokenIndex = i;
            }
            if (tokens[i].equalsIgnoreCase("/to")) {
                toTokenIndex = i;
            }
        }
        if (fromTokenIndex == -1) {
            throw new MissingTokenException("Event", "/from");
        }
        if (toTokenIndex == -1) {
            throw new MissingTokenException("Event", "/to");
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
            EmptyTaskDescriptionException, MissingTokenException {
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
                     | MissingTokenException e) {
                throw new FileCorruptedException("Task File is corrupted...");
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

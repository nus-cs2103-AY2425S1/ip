package nebula.ui;

import nebula.storage.Storage;
import nebula.command.*;
import nebula.exception.NebulaException;
import nebula.task.TaskList;
import nebula.task.TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final DateTimeFormatter DATE_TIME_FORMAT
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DATE_FORMAT
            = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static Ui ui;
    private static Storage storage;
    private static TaskList tasks;

    /**
     * Constructs a new Parser instance.
     * Initializes the user interface and storage, and sets the task list.
     *
     * @param filePath The path to the file where task data is stored.
     * @param tasks    The TaskList containing current tasks.
     */
    public Parser (String filePath, TaskList tasks) {
        ui = new Ui();
        storage = new Storage(filePath);
        this.tasks = tasks;
    }

    /**
     * Parses a command string input by the user and returns the corresponding Command object.
     * It handles commands like "bye", "list", "mark", "unmark", "delete", and task creation commands.
     *
     * @param command The full command string entered by the user.
     * @return The Command object that corresponds to the user input.
     * @throws NebulaException If the command is invalid or improperly formatted.
     */
    public static Command parse(String command) throws NebulaException {
        if(command.equals("bye")) {
            return new ByeCommand(command);
        }
        else if(command.equals("list")) {
            return new ListCommand(command);
        }
        else if(command.startsWith("mark")) {
            validateCommand(command);
            return new MarkCommand(command);
        }
        else if(command.startsWith("unmark")) {
            validateCommand(command);
            return new UnmarkCommand(command);
        }
        else if(command.startsWith("delete")) {
            validateCommand(command);
            return new DeleteCommand(command);
        }
        else if(command.startsWith("find")) {
            validateCommand(command);
            return new FindCommand(command);
        }
        else {
            validateCommand(command);
            TaskType taskType = parseTaskType(command);

            switch (taskType) {
                case TODO:
                    return new AddTodoCommand(command);
                case DEADLINE:
                    return new AddDeadlineCommand(command);
                case EVENT:
                    return new AddEventCommand(command);
            }
        }
        return null;
    }

    /**
     * Validates the user's input for the correct format and content
     *
     * @param command  the user input command
     * @throws NebulaException if the command is invalid or improperly formatted
     */
    public static void validateCommand(String command) throws NebulaException {
        Ui ui = new Ui();

        if (command.isEmpty()) {
            throw new NebulaException("Please enter a command!");
        } else if (!(command.startsWith("todo") || command.startsWith("deadline")
                || command.startsWith("event") || command.startsWith("mark")
                || command.startsWith("unmark") || command.startsWith("delete")
                || command.startsWith("list") || command.startsWith("bye")
                || command.startsWith("find"))) {
            throw new NebulaException(ui.displayUnknownCommandException());
        } else if (command.startsWith("mark") || command.startsWith("unmark")
                || command.startsWith("delete")) {
            parseMarkUnMarkDelete(command);
        } else if (command.startsWith("find")) {
            parseFind(command);
        } else {
            String[] parts = command.split(" ", 2);
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new NebulaException(ui.displayUnknownMessageException());
            }

            String description = parts[1].trim();

            if (command.startsWith("deadline")) {
                parseDeadline(description);
            } else if (command.startsWith("event")) {
                parseEvent(description);
            }
        }
    }

    private static void parseMarkUnMarkDelete(String command) throws NebulaException {
        String[] parts = command.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new NebulaException(ui.displayUnknownTaskNumberException());
        }
        try {
            int taskIndex = Integer.parseInt(parts[1].trim()) - 1;
            if (taskIndex < 0 || taskIndex >= TaskList.getTaskListLength()) {
                throw new NebulaException(ui.displayNonexistentTaskNumberException());
            }
        } catch (NumberFormatException e) {
            throw new NebulaException(ui.displayUnknownTaskNumberException());
        }
    }

    private static void parseFind(String command) throws NebulaException {
        String[] parts = command.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new NebulaException(ui.displayUnknownMessageException());
        }

        String[] keywords = parts[1].trim().split("\\s+");
        if (keywords.length != 1) {
            throw new NebulaException(ui.displayOneKeywordException());
        }
    }

    private static void parseDeadline(String description) throws NebulaException {
        if (!description.contains("/by")) {
            throw new NebulaException(ui.displayUnknownDeadlineException());
        } else {
            // Extract the date after "/by"s
            String[] parts2 = description.split("/by");
            String dueDate = parts2[1].trim();

            // Validate the due date format
            if (!isValidDate(dueDate)) {
                throw new NebulaException("Warning: Deadline date "
                        + "is not in the correct format (M/d/yyyy HHmm).");
            }
        }
    }

    private static void parseEvent(String description) throws NebulaException {
        if (!description.contains("/from") || !description.contains("/to")) {
            throw new NebulaException(ui.displayUnknownEventTimingException());
        } else {
            // Extract dates from the description
            String[] parts2 = description.split("/from");
            String timingPart = parts2[1].trim();
            String[] dates = timingPart.split("/to");

            if (dates.length < 2) {
                throw new NebulaException(ui.displayUnknownEventTimingException());
            }
            String startDate = dates[0].trim();
            String endDate = dates[1].trim();

            // Validate the start and end dates
            if (!isValidDate(startDate) || !isValidDate(endDate)) {
                throw new NebulaException("Warning: Event dates"
                        + " must be in yyyy-mm-dd format.");
            }
        }
    }

    /**
     * Validates if the provided date string is in a valid date format
     *
     * @param dateStr the date string to be validated. It can be in either of two formats
     * @return A boolean representing whether the date string is in a valid format
     */
    private static boolean isValidDate(String dateStr) {
        try {
            LocalDateTime.parse(dateStr, DATE_TIME_FORMAT);
            return true;
        } catch (DateTimeParseException e) {
            try {
                LocalDate.parse(dateStr, DATE_FORMAT);
                return true;
            } catch (DateTimeParseException ex) {
                return false;
            }
        }
    }

    /**
     * Splits the command string to extract the task number
     *
     * @param command The command string containing the command and task number
     * @return The task number as an integer
     */
    public static int splitCommandAndTaskNumber(String command) {
        String taskNum = command.split(" ", 2)[1];
        return Integer.parseInt(taskNum);
    }

    /**
     * Splits the command string to extract the task description
     *
     * @param command The command string containing the command and task description
     * @return The task description as a String
     */
    public static String splitCommandAndTaskDescription(String command) {

        return command.split(" ", 2)[1];
    }

    /**
     * Splits the command string to extract the
     *
     * @param command The command string containing the command, task description, and deadline
     * @return The task deadline as a String
     */
    public static String[] splitDeadlineCommand(String command) {

        return command.split("/by ", 2);
    }

    /**
     *
     * @param command The command string containing the command, task description,
     *                event start time, and event end time
     * @return The event start and end time as a String
     */
    public static String[] splitEventCommand(String command) {

        return command.split(" /", 3);
    }

    /**
     * Determines the TaskType based on the command prefix
     *
     * @param command the input command string
     * @return the corresponding TaskType, or unknown TaskType if unrecognized
     */
    public static TaskType parseTaskType(String command) {
        if (command.startsWith("todo")) {
            return TaskType.TODO;
        } else if (command.startsWith("deadline")) {
            return TaskType.DEADLINE;
        } else if (command.startsWith("event")) {
            return TaskType.EVENT;
        } else {
            return TaskType.UNKNOWN;
        }
    }
}

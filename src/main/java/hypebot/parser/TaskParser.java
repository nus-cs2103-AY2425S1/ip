package hypebot.parser;

import static hypebot.common.Messages.ERROR_LOAD_TASK;
import static hypebot.common.Messages.ERROR_TASK_NAME_EMPTY;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import hypebot.task.Deadline;
import hypebot.task.Event;
import hypebot.task.Task;
import hypebot.task.ToDo;

/**
 * Represents the TaskParser that parses all tasks
 * from user input or saved task files.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class TaskParser {
    /**
     * Takes in the full line user enters and returns the String form of the task name entered.
     *
     * @param line Full String command entered by user.
     * @return String form of the task name entered.
     * @throws ParseException If no task name is entered.
     */
    private static String parseTaskNameFromUi(String line) throws ParseException {
        String[] commandAndTaskName = line.split(" /")[0].split(" ");

        StringBuilder taskNameBuilder = new StringBuilder();
        for (int i = 1; i < commandAndTaskName.length; i++) {
            taskNameBuilder.append(commandAndTaskName[i]).append(" ");
        }

        String taskName = taskNameBuilder.toString().trim();
        if (taskName.isEmpty()) {
            throw new ParseException(ERROR_TASK_NAME_EMPTY, 0);
        }

        return taskName;
    }

    /**
     * Takes in the full line user enters and returns the ToDo task entered.
     *
     * @param fullCommand Full String command entered by user.
     * @return ToDo task entered by user.
     * @throws ParseException If no task name entered.
     */
    public static ToDo parseToDoUi(String fullCommand) throws ParseException {
        String taskName = parseTaskNameFromUi(fullCommand);
        return new ToDo(taskName);
    }

    /**
     * Takes in the full line user enters and returns the Deadline task entered.
     *
     * @param fullCommand Full String command entered by user.
     * @return Deadline task entered by user.
     * @throws ParseException If any of task name or due date not entered.
     * @throws DueDateParseException If due date is not entered in yyyy-mm-dd format.
     * @throws IllegalArgumentException If due date has passed current date.
     */
    public static Deadline parseDeadlineUi(String fullCommand)
            throws ParseException, DueDateParseException, IllegalArgumentException {
        String taskName = parseTaskNameFromUi(fullCommand);
        LocalDate dueDate = DateTimeParser.parseDueDateUi(fullCommand);
        return new Deadline(taskName, dueDate);
    }

    /**
     * Takes in the full line user enters and returns the Event task entered.
     *
     * @param fullCommand Full String command entered by user.
     * @return Event task entered by user.
     * @throws ParseException If any of task name, start time, or end time not entered.
     * @throws EventDateTimeParseException If event times not entered in yyyy-mm-dd hh:mm format.
     * @throws IllegalArgumentException If event has already concluded.
     */
    public static Event parseEventUi(String fullCommand)
            throws ParseException, EventDateTimeParseException, IllegalArgumentException {
        String taskName = parseTaskNameFromUi(fullCommand);
        LocalDateTime[] eventTimes = DateTimeParser.parseEventTimesUi(fullCommand);
        LocalDateTime startTime = eventTimes[0];
        LocalDateTime endTime = eventTimes[1];
        return new Event(taskName, startTime, endTime);
    }

    private static String[] splitFileLine(String line) {
        return line.split(" , ");
    }

    private static String parseTaskTypeFile(String line) {
        return splitFileLine(line)[0];
    }

    private static void checkMarked(String line, Task task) {
        if (splitFileLine(line)[1].equals("1")) {
            task.mark();
        }
    }

    private static String parseTaskNameFile(String line) {
        return splitFileLine(line)[2];
    }

    /**
     * Takes in a String representation of a ToDo task saved on a file,
     * and returns a ToDo task with the corresponding details.
     *
     * @param line String form of a ToDo task outlined in file.
     * @return ToDo task with corresponding details
     */
    private static ToDo parseToDoFile(String line) {
        String taskName = parseTaskNameFile(line);
        return new ToDo(taskName);
    }

    private static String parseDueDateStringFile(String line) {
        return splitFileLine(line)[3];
    }

    /**
     * Takes in a String representation of a Deadline task saved on a file,
     * and returns a Deadline task with the corresponding details.
     *
     * @param line String form of a Deadline task outlined in file.
     * @return Deadline task with corresponding details
     * @throws DueDateParseException If due date encoded in an incorrect format.
     * @throws IllegalArgumentException If due date has passed current date.
     */
    private static Deadline parseDeadlineFile(String line) throws DueDateParseException, IllegalArgumentException {
        String taskName = parseTaskNameFile(line);
        String dueDateString = parseDueDateStringFile(line);
        LocalDate dueDate = DateTimeParser.parseDueDateFile(dueDateString);
        return new Deadline(taskName, dueDate);
    }

    private static String parseStartTimeStringFile(String line) {
        return splitFileLine(line)[3];
    }

    private static String parseEndTimeStringFile(String line) {
        return splitFileLine(line)[4];
    }

    /**
     * Takes in a String representation of an Event task saved on a file,
     * and returns an Event task with the corresponding details.
     *
     * @param line String form of an Event task outlined in file.
     * @return Event task with corresponding details.
     * @throws EventDateTimeParseException If event times encoded in an incorrect format.
     * @throws IllegalArgumentException If event has already concluded.
     */
    private static Event parseEventFile(String line) throws EventDateTimeParseException, IllegalArgumentException {
        String taskName = parseTaskNameFile(line);
        LocalDateTime[] eventTimes = DateTimeParser.parseEventTimesFile(
                parseStartTimeStringFile(line),
                parseEndTimeStringFile(line)
        );
        LocalDateTime startTime = eventTimes[0];
        LocalDateTime endTime = eventTimes[1];
        return new Event(taskName, startTime, endTime);
    }

    /**
     * Takes ina String representation of a Task saved on a file,
     * and returns a Task with the corresponding details
     *
     * @param line String form of a Task outlined in file.
     * @return Task with corresponding details.
     * @throws TaskDateTimeParseException If Deadline's due date or Event times
     *                                    encoded in an incorrect format.
     * @throws IllegalArgumentException If a Deadline's due date has passed current date,
     *                                  or an Event has already concluded.
     */
    public static Task parseTaskFromFile(String line) throws TaskDateTimeParseException, IllegalArgumentException {
        String taskType = parseTaskTypeFile(line);
        Task newTask = switch (taskType) {
        case "T" -> parseToDoFile(line);
        case "D" -> parseDeadlineFile(line);
        case "E" -> parseEventFile(line);
        default -> throw new IllegalArgumentException(ERROR_LOAD_TASK);
        };
        checkMarked(line, newTask);
        return newTask;
    }
}

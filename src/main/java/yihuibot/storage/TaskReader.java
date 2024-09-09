package yihuibot.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import yihuibot.exception.taskformat.DateTimeFormatException;
import yihuibot.exception.taskformat.IncorrectTaskFormatException;
import yihuibot.exception.taskformat.StatusException;
import yihuibot.exception.taskformat.TypeException;
import yihuibot.task.Deadline;
import yihuibot.task.Event;
import yihuibot.task.Task;
import yihuibot.task.TaskList;
import yihuibot.task.Todo;

/**
 * Reads the content of a file and convert it into a TaskList.
 *
 * @author Toh Yi Hui A0259080A
 */
public class TaskReader {
    private Scanner scanner;
    private DateTimeFormatter formatter;

    /**
     * Constructor for a new TaskReader. It takes in a File as parameter and
     * read it, converting date time based on the given date time format.
     *
     * @param file the file containing the data.
     * @param dateTimeFormat the format for converting date time strings.
     * @throws NullPointerException when formatter is null.
     * @throws FileNotFoundException when the file is not found.
     */
    public TaskReader(File file, DateTimeFormatter formatter) throws FileNotFoundException,
            IllegalArgumentException {
        if (formatter == null) {
            throw new NullPointerException("Formatter cannot be null");
        }
        scanner = new Scanner(file);
        this.formatter = formatter;
    }

    /**
     * Reads each line in scanner and covert it into a TaskList.
     *
     * @return the list of tasks.
     * @throws IncorrectTaskFormatException when the content of file is not in
     *                                      the expected format.
     */
    public TaskList read() throws IncorrectTaskFormatException {
        TaskList tasks = new TaskList();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            Task task = parse(line);
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Parse the given line and convert it into a Task.
     *
     * @param line the line data.
     * @return the task.
     * @throws IncorrectTaskFormatException when the content of file is not in
     *                                      the expected format.
     */
    private Task parse(String line) throws IncorrectTaskFormatException {
        String[] array = line.split(" \\| ");

        String type = readType(array);
        boolean isComplete = readIsComplete(array);
        String description = readDescription(array);

        assert type.equals("T") || type.equals("D") || type.equals("E")
                : "Incorrect task type detected";

        switch (type) {
        case ("T"):
            return new Todo(description, isComplete);
        case ("D"):
            LocalDateTime deadline = readDeadline(array);
            return new Deadline(description, isComplete, deadline);
        case ("E"):
            LocalDateTime startTime = readStartTime(array);
            LocalDateTime endTime = readEndTime(array);
            return new Event(description, isComplete, startTime, endTime);
        default:
            throw new TypeException();
        }
    }

    /**
     * Read the type of the task based on the given line.
     *
     * @param array the line data split with the " \\| " delimiter.
     * @return the type of the task in String (either "T", "D", or "E").
     * @throws IncorrectTaskFormatException when unable to find task type, or
     *                                      when the type is not "T" or "D" or "E".
     */
    private String readType(String[] array) throws IncorrectTaskFormatException {
        if (array == null || array.length < 1) {
            throw new IncorrectTaskFormatException("Cannot find task type in data.");
        }

        String type = array[0];
        if (!type.equals("T") && !type.equals("D") && !type.equals("E")) {
            throw new TypeException();
        }

        return type;
    }

    /**
     * Read whether the task is completed.
     *
     * @param array the line data split with the " \\| " delimiter.
     * @return true for 1, false for 0.
     * @throws IncorrectTaskFormatException when unable to find task status, or
     *                                      when the data is not "1" or "0".
     */
    private boolean readIsComplete(String[] array) throws IncorrectTaskFormatException {
        if (array == null || array.length < 2) {
            throw new IncorrectTaskFormatException("Cannot find task status in data.");
        }

        String isComplete = array[1];
        if (!isComplete.equals("0") && !isComplete.equals("1")) {
            throw new StatusException();
        }

        return !isComplete.equals("0");
    }

    /**
     * Read the description of the task.
     *
     * @param array the line data split with the " \\| " delimiter.
     * @return the description of the task.
     * @throws IncorrectTaskFormatException when unable to find task description.
     */
    private String readDescription(String[] array) throws IncorrectTaskFormatException {
        if (array == null || array.length < 3) {
            throw new IncorrectTaskFormatException("Cannot find task description in data.");
        }

        return array[2];
    }

    /**
     * Convert the given String into a LocalDateTime.
     *
     * @return a LocalDateTime object.
     * @throws DateTimeFormatException when s cannot be parsed as a LocalDateTime.
     */
    private LocalDateTime parseTime(String s) throws DateTimeFormatException {
        try {
            return LocalDateTime.parse(s, formatter);
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException(s, formatter);
        }
    }

    /**
     * Read the deadline of a Deadline task.
     *
     * @param array the line data split with the " \\| " delimiter.
     * @return the deadline of the task in a LocalDateTime object.
     * @throws IncorrectTaskFormatException when unable to find deadline of task, or
     *                                      when data cannot be parsed as a LocalDateTime.
     */
    private LocalDateTime readDeadline(String[] array) throws IncorrectTaskFormatException {
        if (array == null || array.length < 4) {
            throw new IncorrectTaskFormatException("Cannot find task deadline in data.");
        }

        return parseTime(array[3]);
    }

    /**
     * Read the start time of an Event task.
     *
     * @param array the line data split with the " \\| " delimiter.
     * @return the start time of the task in a LocalDateTime object.
     * @throws IncorrectTaskFormatException when unable to find start time of task, or
     *                                      when data cannot be parsed as a LocalDateTime.
     */
    private LocalDateTime readStartTime(String[] array) throws IncorrectTaskFormatException {
        if (array == null || array.length < 4) {
            throw new IncorrectTaskFormatException("Cannot find start time of Event in data.");
        }

        return parseTime(array[3]);
    }

    /**
     * Read the end time of an Event task.
     *
     * @param array the line data split with the " \\| " delimiter.
     * @return the end time of the task in a LocalDateTime object.
     * @throws IncorrectTaskFormatException when unable to find end time of task, or
     *                                      when data cannot be parsed as a LocalDateTime.
     */
    private LocalDateTime readEndTime(String[] array) throws IncorrectTaskFormatException {
        if (array == null || array.length < 5) {
            throw new IncorrectTaskFormatException("Cannot find end time of Event in data.");
        }

        return parseTime(array[4]);
    }
}

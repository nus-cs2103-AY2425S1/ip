import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import exception.IncorrectTaskFormatException;

/**
 * Reads the content of a file and convert it into an ArrayList of Task.
 *
 * @author Toh Yi Hui A0259080A
 */
public class TaskReader {
    private File file;
    private Scanner scanner;
    private DateTimeFormatter formatter;

    /**
     * Constructor for a new TaskReader. It takes in a File as parameter and
     * read it, converting date time based on the given date time format.
     *
     * @param file the file containing the data.
     * @param dateTimeFormat the format for converting date time strings.
     * @throws FileNotFoundException when the file is not found.
     * @throws IllegalArgumentException if the dateTimeFormat is not a proper pattern.
     */
    public TaskReader(File file, String dateTimeFormat) throws FileNotFoundException,
           IllegalArgumentException {
        this.file = file;
        scanner = new Scanner(file);
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    /**
     * Reads each line in scanner and covert it into an ArrayList of Task.
     *
     * @return the list of tasks.
     * @throws NullPointerException when scanner or formatter is null.
     * @throws IncorrectTaskFormatException when the content of file is not in
     *                                      the expected format.
     */
    public ArrayList<Task> read() throws NullPointerException, IncorrectTaskFormatException {
        if (scanner == null) {
            throw new NullPointerException("Scanner cannot be null.");
        }

        ArrayList<Task> tasks = new ArrayList<>();
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
     * @throws NullPointerException when formatter is null.
     * @throws IncorrectTaskFormatException when the content of file is not in
     *                                      the expected format.
     */
    private Task parse(String line) throws NullPointerException, IncorrectTaskFormatException {
        String[] array = line.split(" \\| ");

        String type = readType(array);
        boolean isComplete = readIsComplete(array);
        String description = readDescription(array);

        switch (type) {
        case ("T"):
            return new Todo(description, isComplete);
        case ("D"):
            LocalDateTime dateTime = readDateTime(array);
            return new Deadline(description, isComplete, dateTime);
        case ("E"):
            LocalDateTime startTime = readStartTime(array);
            LocalDateTime endTime = readEndTime(array);
            return new Event(description, isComplete, startTime, endTime);
        default:
            throw new IncorrectTaskFormatException(file.getAbsolutePath());
        }
    }

    /**
     * Read the type of the task based on the given line.
     *
     * @param array the line data split with the " | " delimiter.
     * @return the type of the task in String (either "T", "D", or "E").
     * @throws IncorrectTaskFormatException when the type is not "T" or "D" or "E".
     */
    private String readType(String[] array) throws IncorrectTaskFormatException {
        if (array == null || array.length < 1) {
            throw new IncorrectTaskFormatException(file.getAbsolutePath());
        }

        String type = array[0];
        if (!type.equals("T") && !type.equals("D") && !type.equals("E")) {
            throw new IncorrectTaskFormatException(file.getAbsolutePath());
        }

        return type;
    }

    /**
     * Read whether the task is completed.
     *
     * @param array the line data split with the " | " delimiter.
     * @return true for 1, false for 0.
     * @throws IncorrectTaskFormatException when the data is not "1" or "0".
     */
    private boolean readIsComplete(String[] array) throws IncorrectTaskFormatException {
        if (array == null || array.length < 2) {
            throw new IncorrectTaskFormatException(file.getAbsolutePath());
        }

        String isComplete = array[1];
        if (!isComplete.equals("0") && !isComplete.equals("1")) {
            throw new IncorrectTaskFormatException(file.getAbsolutePath());
        }

        return !isComplete.equals("0");
    }

    /**
     * Read the description of the task.
     *
     * @param array the line data split with the " | " delimiter.
     * @return the description of the task.
     * @throws IncorrectTaskFormatException when the data given is not correct.
     */
    private String readDescription(String[] array) throws IncorrectTaskFormatException {
        if (array == null || array.length < 3) {
            throw new IncorrectTaskFormatException(file.getAbsolutePath());
        }

        return array[2];
    }

    /**
     * Convert the given String into a LocalDateTime.
     *
     * @return a LocalDateTime object.
     * @throws NullPointerException when formatter is null.
     * @throws IncorrectTaskFormatException when the data string cannot be parsed as a LocalDateTime.
     */
    private LocalDateTime parseTime(String s) throws NullPointerException, IncorrectTaskFormatException {
        if (formatter == null) {
            throw new NullPointerException("Formatter cannot be null.");
        }

        try {
            return LocalDateTime.parse(s, formatter);
        } catch (DateTimeParseException e) {
            throw new IncorrectTaskFormatException(file.getAbsolutePath());
        }
    }

    /**
     * Read the date time of a dateline task.
     *
     * @param array the line data split with the " | " delimiter.
     * @return the date time of the task in a LocalDateTime object.
     * @throws NullPointerException when formatter is null.
     * @throws IncorrectTaskFormatException when the data cannot be parsed as a LocalDateTime.
     */
    private LocalDateTime readDateTime(String[] array) throws NullPointerException,
            IncorrectTaskFormatException {
        if (array == null || array.length < 4) {
            throw new IncorrectTaskFormatException(file.getAbsolutePath());
        }

        return parseTime(array[3]);
    }

    /**
     * Read the start time of an Event task.
     *
     * @param array the line data split with the " | " delimiter.
     * @return the date time of the task in a LocalDateTime object.
     * @throws NullPointerException when formatter is null.
     * @throws IncorrectTaskFormatException when the data cannot be parsed as a LocalDateTime.
     */
    private LocalDateTime readStartTime(String[] array) throws NullPointerException,
            IncorrectTaskFormatException {
        // Exact same function as readDateTime.
        return readDateTime(array);
    }

    /**
     * Read the end time of an Event task.
     *
     * @param array the line data split with the " | " delimiter.
     * @return the date time of the task in a LocalDateTime object.
     * @throws NullPointerException when formatter is null.
     * @throws IncorrectTaskFormatException when the data cannot be parsed as a LocalDateTime.
     */
    private LocalDateTime readEndTime(String[] array) throws NullPointerException,
            IncorrectTaskFormatException {
        if (array == null || array.length < 5) {
            throw new IncorrectTaskFormatException(file.getAbsolutePath());
        }

        return parseTime(array[4]);
    }
}

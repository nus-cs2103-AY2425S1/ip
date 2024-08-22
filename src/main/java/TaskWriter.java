import java.util.ArrayList;

import java.io.FileWriter;
import java.io.IOException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * Store the given tasks in the given file path. Output of this file must be
 * readable by TaskReader.
 *
 * @author Toh Yi Hui A0259080A
 */
public class TaskWriter {
    private FileWriter fileWriter;
    private DateTimeFormatter formatter;
    private ArrayList<Task> tasks;

    /**
     * Constructor for a new TaskWriter. It takes a file path and an
     * ArrayList of tasks as parameter, and write it to file using given
     * date time format.
     *
     * @param path the file path to write to.
     * @param dateTimeFormat the format for converting date time to string.
     * @param tasks the ArrayList of tasks to be copied.
     * @throws IOException if the named file exists but is a directory rather
     *                     than a regular file, does not exist but cannot be
     *                     created, or cannot be opened for any other reason.
     *                     (Paragraph taken from FileWriter java docs Oracle)
     * @throws IllegalArgumentException if the given date time format is not a
     *                                  valid pattern.
     */
    public TaskWriter(String path, String dateTimeFormat, ArrayList<Task> tasks)
            throws IOException, IllegalArgumentException {
        fileWriter = new FileWriter(path);
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.tasks = tasks;
    }

    /**
     * Write the tasks to file.
     *
     * @throws IOException if an I/O error occurs.
     * @throws NullPointerException when fileWriter, formatter, or tasks is null.
     * @throws DateTimeException if the value given to any date time fields is out of range.
     */
    public void write() throws IOException, NullPointerException, DateTimeException {
        if (fileWriter == null || tasks == null) {
            throw new NullPointerException("fileWriter and tasks cannot be null.");
        }

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String isComplete = task.isComplete() ? "1" : "0";
            String description = task.getDescription();

            if (task instanceof Todo) {
                String type = "T";
                fileWriter.write(type + " | " + isComplete + " | " + description
                        + "\n");
            } else if (task instanceof Deadline) {
                Deadline temp = (Deadline) task;
                String type = "D";
                String deadline = parseDateTime(temp.getDeadline());
                fileWriter.write(type + " | " + isComplete + " | " + description
                        + " | " + deadline + "\n");
            } else if (task instanceof Event) {
                Event temp = (Event) task;
                String type = "E";
                String start = parseDateTime(temp.getStart());
                String end = parseDateTime(temp.getEnd());
                fileWriter.write(type + " | " + isComplete + " | " + description
                        + " | " + start + " | " + end + "\n");
            }
        }
    }

    /**
     * Converts the LocalDateTime object into a String, which can be converted
     * back to a LocalDatetime object using the same formatter.
     *
     * @param dateTime the LocalDateTime to convert.
     * @return the String representation of the LocalDateTime.
     * @throws NullPointerException if the formatter is null.
     * @throws DateTimeException if the value given to any date time fields is out of range.
     */
    private String parseDateTime(LocalDateTime dateTime) throws NullPointerException, DateTimeException {
        if (formatter == null) {
            throw new NullPointerException("Formatter cannot be null.");
        }

        return dateTime.format(formatter);
    }

    /**
     * Closes the file.
     */
    public void close() throws IOException {
        fileWriter.close();
    }
}

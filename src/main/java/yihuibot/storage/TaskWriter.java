package yihuibot.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import yihuibot.task.Deadline;
import yihuibot.task.Event;
import yihuibot.task.Task;
import yihuibot.task.TaskList;
import yihuibot.task.Todo;

/**
 * Store the given tasks into file. Output of this file must be
 * readable by TaskReader.
 *
 * @author Toh Yi Hui A0259080A
 */
public class TaskWriter {
    private FileWriter fileWriter;
    private DateTimeFormatter formatter;
    private TaskList tasks;

    /**
     * Constructor for a new TaskWriter. It takes a File, a DateTimeFormatter
     * and an ArrayList of tasks as parameter, and write it to file using given
     * DateTimeFormatter when write() is called.
     *
     * @param file the File to write to.
     * @param formatter the formatter for converting LocalDateTime to String.
     * @param tasks the TaskList to be copied.
     * @throws IOException if the named file exists but is a directory rather
     *                     than a regular file, does not exist but cannot be
     *                     created, or cannot be opened for any other reason.
     *                     (Paragraph taken from FileWriter java docs Oracle)
     * @throws NullPointerException when formatter or tasks is null.
     */
    public TaskWriter(File file, DateTimeFormatter formatter, TaskList tasks)
            throws IOException, NullPointerException {
        if (formatter == null) {
            throw new NullPointerException("Formatter cannot be null.");
        }

        if (tasks == null) {
            throw new NullPointerException("Tasks cannot be null.");
        }

        fileWriter = new FileWriter(file);
        this.formatter = formatter;
        this.tasks = tasks;
    }

    /**
     * Write the tasks to file.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void write() throws IOException {
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
     */
    private String parseDateTime(LocalDateTime dateTime) {
        return dateTime.format(formatter);
    }

    /**
     * Closes the file.
     *
     * @throws IOException if a I/O error occurs.
     */
    public void close() throws IOException {
        fileWriter.close();
    }
}

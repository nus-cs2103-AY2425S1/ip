import java.util.ArrayList;

import java.io.FileWriter;
import java.io.IOException;

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
    private ArrayList<Task> tasks;

    /**
     * Constructor for a new TaskWriter. It takes a file path as and an
     * ArrayList of tasks as parameter.
     *
     * @param path the file path to write to.
     * @param tasks the ArrayList of tasks to be copied.
     * @throws IOException if the named file exists but is a directory rather
     *                     than a regular file, does not exist but cannot be
     *                     created, or cannot be opened for any other reason.
     *                     (Paragraph taken from FileWriter java docs Oracle)
     */
    public TaskWriter(String path, ArrayList<Task> tasks) throws IOException {
        fileWriter = new FileWriter(path);
        this.tasks = tasks;
    }

    /**
     * Write the tasks to file.
     *
     * @throws IOException if an I/O error occurs.
     * @throws NullPointerException when fileWriter or tasks is null.
     */
    public void write() throws IOException, NullPointerException {
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
                String deadline = temp.getDeadline();
                fileWriter.write(type + " | " + isComplete + " | " + description
                        + " | " + deadline + "\n");
            } else if (task instanceof Event) {
                Event temp = (Event) task;
                String type = "E";
                String start = temp.getStart();
                String end = temp.getEnd();
                fileWriter.write(type + " | " + isComplete + " | " + description
                        + " | " + start + " | " + end + "\n");
            }
        }
    }

    /**
     * Closes the file.
     */
    public void close() throws IOException {
        fileWriter.close();
    }
}

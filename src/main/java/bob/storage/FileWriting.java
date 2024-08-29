package bob.storage;

import bob.data.TaskList;
import bob.tasks.Task;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Writes list of tasks to a file.
 */
public class FileWriting extends Storage {
    private static final String SEPARATOR = " | ";

    /**
     * Creates a new FileWriting object.
     *
     * @param filePath The path to the file.
     */
    public FileWriting(String filePath) {
        super(filePath);
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param updatedList The list of tasks to be saved.
     * @throws IOException If there is an error writing to the file.
     */
    public static void saveTasks(TaskList updatedList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : updatedList) {
            fw.write(formatTasks(t) + "\n");
        }
        fw.close();
    }

    /**
     * Formats a Task object into a string.
     *
     * @param t The Task object to be formatted.
     * @return A string representing the Task object.
     */
    protected static String formatTasks(Task t) {
        String type = t.getType();
        int status = t.isDone? 1 : 0;
        String description = t.getDescription();
        String formatted = type + SEPARATOR + status + SEPARATOR + description;

        if (type.equals("D")) {
            String by = t.getBy();
            formatted = formatted + SEPARATOR + by;
        }

        if (type.equals("E")) {
            String from = t.getFrom();
            String to = t.getTo();
            formatted = formatted + SEPARATOR + from + SEPARATOR + to;
        }
        return formatted;
    }
}

package bob.storage;

import java.io.FileWriter;
import java.io.IOException;

import bob.data.TaskList;
import bob.tasks.Task;

/**
 * Represents the file writing.
 */
public class FileWriting extends Storage {
    private static final String SEPARATOR = " | ";

    /**
     * Creates a new FileWriting object.
     *
     * @param filePath the path to the file.
     */
    public FileWriting(String filePath) {
        super(filePath);
    }

    /**
     * Saves the tasks to the file.
     *
     * @param updatedList the updated list of tasks.
     * @throws IOException if the file cannot be saved.
     */
    public static void saveTasks(TaskList updatedList) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (Task t : updatedList) {
            fw.write(formatTasks(t) + "\n");
        }
        fw.close();
    }
    protected static String formatTasks(Task t) {
        String type = t.getType();
        int status = t.getIsDone() ? 1 : 0;
        String description = t.getDescription();
        String formatted = type + SEPARATOR + status + SEPARATOR + description;

        if (type == "D") {
            assert type == "D" : "Type should be 'D' but was '" + type + "'";
            String by = t.getBy();
            assert by != null : "By should not be null";
            formatted = formatted + SEPARATOR + by;
        }

        if (type == "E") {
            assert type == "E" : "Type should be 'E' but was '" + type + "'";
            String from = t.getFrom();
            assert from != null : "From should not be null";
            String to = t.getTo();
            assert to != null : "To should not be null";
            formatted = formatted + SEPARATOR + from + SEPARATOR + to;
        }
        return formatted;
    }
}

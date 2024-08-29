package bob.storage;

import java.io.FileWriter;
import java.io.IOException;

import bob.data.TaskList;
import bob.tasks.Task;

public class FileWriting extends Storage {
    private static final String SEPARATOR = " | ";

    public FileWriting(String filePath) {
        super(filePath);
    }

    public static void saveTasks(TaskList updatedList) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (Task t : updatedList) {
            fw.write(formatTasks(t) + "\n");
        }
        fw.close();
    }
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

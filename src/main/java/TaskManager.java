import java.io.IOException;
import java.lang.StringBuilder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TaskManager {
    private static final Path taskFile = Paths.get("./task/task.txt");

    private static String readTaskList(ArrayList<Task> tasks) {
        StringBuilder taskString = new StringBuilder();
        for (Task task: tasks) {
            taskString.append(task.getSaveMessage());
            taskString.append("\n");
        }
        return taskString.toString();
    }

    public static void initialize() {
        try {
            Files.createDirectories(taskFile.getParent());
            if (!Files.exists(taskFile)) {
                Files.createFile(taskFile);
            }
        } catch (IOException e) {
            System.out.println("Error when creating task file...");
        }
    }

    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            String taskString = readTaskList(tasks);
            Files.writeString(taskFile, taskString);
        } catch (IOException e) {
            System.out.println("Error when saving tasks...");
        }
    }
}
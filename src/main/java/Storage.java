import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Task.Task;

public class Storage {
    private static final String TASK_FILE_DIRECTORY = "./data/taskTable.txt";
    private static final File TASK_FILE = new File(TASK_FILE_DIRECTORY);

    public static void saveTasksToLocalDisk(ArrayList<Task> tasks) {
        try {
            writeToTaskFile(formatTasks(tasks));
        } catch (IOException e) {
            System.out.println("Failed to save the list of tasks to local disk.");
        }
    }

    private static String formatTasks(ArrayList<Task> tasks) {
        StringBuilder res = new StringBuilder();
        for (Task t: tasks) {
            res.append(t.formatToCSV());
            res.append("\n");
        }
        return res.toString();
    }

    private static void createTaskFile() {
        try {
            File parentDir = TASK_FILE.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            if (TASK_FILE.createNewFile()) {
                System.out.println("Task file created.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred when creating the task file.");
        }
    }

    private static void writeToTaskFile(String formattedTasks) throws IOException {
        if (TASK_FILE.isFile()) {
            try (FileWriter writer = new FileWriter(TASK_FILE_DIRECTORY)) {
                writer.write(formattedTasks);
                System.out.println("Successfully written to task file.");
            } catch (IOException e) {
                System.out.println("An error occured while writing to the task file.");
            }
        } else {
            createTaskFile();
            writeToTaskFile(formattedTasks);
        }
    }
}

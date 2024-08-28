import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class TaskManagerFile {
    public static void saveTasksToFile(TaskManager taskManager, String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(filePath);
            Iterator<Task> taskIterator = taskManager.getTasksIterator();
            for (int i = 0; taskIterator.hasNext(); i++) {
                Task task = taskIterator.next();
                fileWriter.write(String.format("%s%n", task.toDataString()));
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadTasksFromFile(TaskManager taskManager, String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String taskString = scanner.nextLine();
                Task task = TaskDecoder.parseTask(taskString);
                taskManager.addTask(task, false);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

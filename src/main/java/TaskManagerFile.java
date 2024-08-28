import java.util.FileWriter;
import java.util.IOException;

public class TaskManagerFile {
    public static void saveTasksToFile(TaskManager taskManager, String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : taskManager.getTasks()) {
                fileWriter.write(String.format("%s%n", task.toDataString()));
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadTasksFromFile(TaskManager taskManager, String filePath) {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String taskString = scanner.nextLine();
                Task task = TaskDecoder.parseTask(taskString);
                taskManager.addTask(task);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

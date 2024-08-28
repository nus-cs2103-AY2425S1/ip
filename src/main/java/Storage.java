import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final String DIRECTORY = "./data";
    private String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    public void saveToDisk(TaskList taskList) {
        File dir = new File(DIRECTORY);
        File file = new File(dir, fileName);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : taskList.getTasks()) {
                writer.write(task.saveString() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TaskList loadFromDisk() {
        File file = new File(DIRECTORY, fileName);

        if (!file.exists()) {
            return new TaskList(fileName);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            ArrayList<Task> tasks = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromString(line);
                tasks.add(task);
            }
            return new TaskList(tasks, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new TaskList(fileName);
        }
    }
}
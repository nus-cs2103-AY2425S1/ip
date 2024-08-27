import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void loadTasks(ArrayList<Task> taskList) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task;
                if (line.startsWith("T")) {
                    task = ToDo.parseTask(line);
                }
                else if (line.startsWith("D")) {
                    task = Deadline.parseTask(line);
                }
                else if (line.startsWith("E")) {
                    task = Event.parseTask(line);
                }
                else {
                    throw new IllegalArgumentException("Unknown task type");
                }
                taskList.add(task);
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred while loading tasks from file.");
        }
    }

    public void saveTasks(ArrayList<Task> taskList) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : taskList) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
            writer.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred while saving tasks to file.");
        }
    }
}

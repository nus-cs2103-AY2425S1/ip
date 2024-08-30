import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList loadTasks() {
        TaskList tasks = new TaskList();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks; // Return empty list if file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Task task = Parser.parseFile(line);
                    tasks.addTask(task);
                } catch (Exception e) {
                    System.out.println("Warning: Corrupted line ignored.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    public void saveTasks(TaskList tasks) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs(); // Create directory if it doesn't exist
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (int i = 0; i < tasks.getSize(); i++) {
                    writer.write(tasks.getTask(i).toFileFormat() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}

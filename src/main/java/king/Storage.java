package king;

import king.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    private TaskList taskList = new TaskList(); //store tasklist

    public Storage(String filePath) {
        this.filePath = filePath;
        ensureDirectoryExists();
    }

    private void ensureDirectoryExists() {
        File directory = new File(new File(filePath).getParent());
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public void save(ArrayList<Task> tasks) throws KingException {
        try (FileWriter writer = new FileWriter(this.filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new KingException("An error occurred while saving tasks.");
        }
    }

    public ArrayList<Task> loadTasks() throws KingException {
        File file = new File(this.filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        if (!file.exists()) {
            return tasks; // File does not exist; return empty list
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Task.fromFileString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new KingException("An error occurred while loading tasks.");
        }
        return tasks;
    }
}

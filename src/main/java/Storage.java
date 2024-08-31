import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs(); // Create directory if it does not exist
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    tasks.add(parseTask(line));
                }
            } catch (IOException e) {
                System.out.println("Error loading tasks from file: " + e.getMessage());
            }
        }
        return tasks;
    }

    public void saveTasks(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toDataString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private Task parseTask(String data) {
        String[] parts = data.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
            case "T":
                return new Todo(description, isDone);
            case "D":
                String by = parts[3];
                return new Deadline(description, by, isDone);
            case "E":
                String[] subparts = parts[3].split(" - ");
                String from = subparts[0];
                String to = subparts[1];
                return new Event(description, from, to, isDone);
            default:
                return null;
        }
    }
}
package storage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StorageManager {
    private String filePath;

    public StorageManager(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasks(List<Task> tasks) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // Create the directory if it doesn't exist

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        }
    }

    public List<Task> loadTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks; // Return empty list if the file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error parsing task data: " + e.getMessage());
        }

        return tasks;
    }

    private Task parseTask(String line) {
        // Logic to parse the task from file format
        // Example: "T | 1 | read book" => Create a Todo task
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T":
                return new Todo(description, isDone);
            case "D":
                String by = parts[3];
                return new Deadline(description, by, isDone);
            case "E":
                String from = parts[3];
                String to = parts[4];
                return new Event(description, from, to, isDone);
            default:
                throw new IllegalArgumentException("Invalid task type in file.");
        }
    }
}

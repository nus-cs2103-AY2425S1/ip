import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class TaskManager {
    private final ArrayList<Task> tasks;
    private final String filePath = "./data/jade.txt";

    public TaskManager() {
        tasks = new ArrayList<>();
        loadTasksFromFile();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markTask(int index, boolean isDone) {
        if (isValidTaskIndex(index)) {
            if (isDone) {
                tasks.get(index).markAsDone();
            } else {
                tasks.get(index).markAsNotDone();
            }
            saveTasksToFile();
        }
    }

    public void deleteTask(int index) throws JadeException {
        if (isValidTaskIndex(index)) {
            tasks.remove(index);
            saveTasksToFile();
        } else {
            throw new JadeException("Hmm, no such task. Try again.");
        }
    }

    public boolean isValidTaskIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    public int getTaskCount() {
        return tasks.size();
    }

    private void loadTasksFromFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("No existing task file. Starting fresh.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                Task task = parseTaskFromString(parts);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading the file: " + e.getMessage());
        }
    }

    private Task parseTaskFromString(String[] parts) {
        try {
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
                    System.out.println("Unknown task type found in file. Skipping.");
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Corrupted task data. Skipping");
            return null;
        }
    }

    private void saveTasksToFile() {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // if folder-does-not-exist-yet, create one

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toDataString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error while saving tasks: " + e.getMessage());
        }
    }
}
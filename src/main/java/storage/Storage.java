package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

/**
 * Handles the loading and saving of tasks to and from a file.
 */
public class Storage {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
    private final String filePath;

    /**
     * Constructs a Storage object with specified file path.
     * If the file or its directories does not exist, it creates them.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        createFileIfNotExists(filePath);
    }

    /**
     * Created the file and its parent directories if they do not already exist.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    private void createFileIfNotExists(String filePath) {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + filePath);
                } else {
                    System.out.println("File already exists: " + filePath);
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + filePath);
                e.printStackTrace();
            }
        }
    }

    /**
     * Saves the list of tasks to the file specified by the file path.
     * Each task is saved in a format suitable for loading later.
     *
     * @param taskList The list of tasks to be saved.
     */
    public void saveTasks(ArrayList<Task> taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : taskList) {
                writer.write(task.toSaveFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from the file specified by the file path.
     * Tasks are raad line by line and converted to their corresponding tasks object.
     *
     * @return An ArrayList of tasks loaded from the file.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> taskList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] components = line.split("\\|");
                String taskType = components[0].trim();
                boolean isDone = components[1].trim().equals("Done");
                String description = components[2].trim();
                Task task = null;

                if (taskType.equalsIgnoreCase("task.ToDo")) {
                    task = new ToDo(description);
                } else if (taskType.equalsIgnoreCase("task.Event")) {
                    LocalDateTime startDate = LocalDateTime.parse(components[3].trim(), FORMATTER);
                    LocalDateTime endDate = LocalDateTime.parse(components[4].trim(), FORMATTER);
                    task = new Event(description, startDate, endDate);
                } else if (taskType.equalsIgnoreCase("task.Deadline")) {
                    LocalDateTime by = LocalDateTime.parse(components[3].trim(), FORMATTER);
                    task = new Deadline(description, by);
                } else {
                    System.out.println("Unknown task type");
                }

                if (task != null && isDone) {
                    task.markAsDone();
                }
                taskList.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error reading tasks from file.");
            e.printStackTrace();
        }
        return taskList;
    }
}
package storage;

import task.Task;
import task.ToDo;
import task.Event;
import task.Deadline;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import task.Task;

public class Storage {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        createFileIfNotExists(filePath);
    }

    private void createFileIfNotExists(String filePath) {
        File file = new File(filePath);
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

    public void saveTasks(ArrayList<Task> taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            for (Task task : taskList) {
                writer.write(task.toSaveFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
            e.printStackTrace();
        }
    }

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

package utilities;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {

    public String rootPath;
    private TaskList tasks = new TaskList();
    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public Storage (String filename) {
        this.rootPath = filename;
    }
    protected void saveToFile() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rootPath))) {
            for (Task task : tasks) {
                writer.write(task.toSaveFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    protected void loadFromFile() {
        File file = new File(rootPath);
        if (!file.exists()) {
            return;  // No file to load from
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task = null;
                switch (type) {
                    case "T":
                        task = new Todo(description);
                        break;
                    case "D":
                        LocalDateTime by = LocalDateTime.parse(parts[3], DATE_FORMATTER);
                        task = new Deadline(description, by);
                        break;
                    case "E":
                        LocalDateTime from = LocalDateTime.parse(parts[3], DATE_FORMATTER);
                        LocalDateTime to = LocalDateTime.parse(parts[4], DATE_FORMATTER);
                        task = new Event(description, from, to);
                        break;
                }

                if (task != null) {
                    if (isDone) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }
    protected ArrayList<Task> getTasks() {
        return this.tasks;
    }

}
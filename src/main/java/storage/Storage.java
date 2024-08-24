package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import exceptions.InvalidTaskException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Storage {
    private static final String directoryPath = "./data";
    private final File file;

    public Storage(String fileName) {
        String filePath = String.format("%s/%s", directoryPath, fileName);
        this.file = new File(filePath);
    }

    public ArrayList<Task> load() {
        ensureFileExists();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            ArrayList<Task> tasks = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = convertStringToTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println("No data file found");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public void save(ArrayList<Task> tasks) {
        ensureFileExists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private void ensureFileExists() {
        try {
            if (!file.exists()) {
                this.file.getParentFile().mkdirs();
                this.file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
    }

    private Task convertStringToTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) {
                throw new InvalidTaskException("Missing data in:" + line);
            }

            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            Task task;

            switch (taskType) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                if (parts.length < 4) {
                    throw new InvalidTaskException("Deadline missing for: " + line);
                }
                String deadline = parts[3];
                task = new Deadline(description, deadline);
                break;
            case "E":
                if (parts.length < 4) {
                    throw new InvalidTaskException("Event date missing for: " + line);
                }

                String date = parts[3];
                String[] dateParts = date.split("-");
                if (dateParts.length != 2) {
                    throw new InvalidTaskException("Invalid date format for: " + line);
                }
                task = new Event(description, dateParts[0], dateParts[1]);
                break;
            default:
                throw new InvalidTaskException("Invalid task type for: " + line);
            }
            task.setDone(isDone);
            return task;
        } catch (InvalidTaskException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

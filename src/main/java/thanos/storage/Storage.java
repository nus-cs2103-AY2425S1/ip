package thanos.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import thanos.exceptions.InvalidDateException;
import thanos.exceptions.InvalidTaskException;
import thanos.tasks.Deadline;
import thanos.tasks.Event;
import thanos.tasks.Task;
import thanos.tasks.Todo;
import thanos.utility.DateTimeUtility;

/**
 * Handles the loading and saving of tasks to and from a file.
 * <p>
 * The {@code Storage} class implements the {@code IStorage} interface to manage
 * reading from and writing to a file, ensuring that task data is preserved
 * across application runs.
 * </p>
 */
public class Storage implements IStorage {
    /**
     * The directory path where the data file is stored.
     */
    private static final String directoryPath = "./data";

    /**
     * The file that stores the task data.
     */
    private final File file;

    /**
     * Constructs a {@code Storage} object with the specified file name.
     * <p>
     * This constructor initializes the file path for storing task data,
     * creating a {@code File} object to represent the file.
     * </p>
     *
     * @param fileName the name of the file to store task data.
     * @throws AssertionError if the fileName provided is empty and assertions are enabled.
     */
    public Storage(String fileName) {
        assert !fileName.isEmpty() : "File name must not be empty";
        String filePath = String.format("%s/%s", directoryPath, fileName);
        this.file = new File(filePath);
    }

    /**
     * Loads tasks from the file.
     * <p>
     * This method reads the file line by line, converting each line to a {@code Task}
     * object. It returns a list of tasks loaded from the file. If an error occurs,
     * it returns an empty list.
     * </p>
     *
     * @return an {@code ArrayList} of {@code Task} objects read from the file.
     */
    @Override
    public ArrayList<Task> load() {
        ensureFileExists();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            ArrayList<Task> taskList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = convertStringToTask(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
            return taskList;
        } catch (FileNotFoundException e) {
            System.out.println("No data file found");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Saves the given list of tasks to the file.
     * <p>
     * This method writes each task's string representation to the file,
     * with each task on a new line. If an error occurs during writing,
     * it prints an error message to the console.
     * </p>
     *
     * @param taskList the list of {@code Task} objects to be saved to the file.
     */
    @Override
    public void save(ArrayList<Task> taskList) {
        ensureFileExists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : taskList) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Ensures that the file exists and creates it if necessary.
     * <p>
     * This method checks if the file's parent directories exist and creates them if they don't.
     * It also creates a new file if it does not already exist.
     * </p>
     */
    private void ensureFileExists() {
        try {
            if (!file.exists()) {
                if (!this.file.getParentFile().mkdirs()) {
                    throw new IOException("Failed to create the necessary directories for the file");
                }
                if (!this.file.createNewFile()) {
                    throw new IOException("Failed to create file");
                }
            }
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
    }

    /**
     * Converts a string representation of a task to a {@code Task} object.
     * <p>
     * This method parses a line from the file into a {@code Task} object based on its type and data.
     * It handles different task types (Todo, Deadline, Event) and validates the data.
     * </p>
     *
     * @param line the string representation of the task from the file.
     * @return a {@code Task} object if the string is valid; {@code null} otherwise.
     */

    private Task convertStringToTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) {
                throw new InvalidTaskException("Missing data in:" + line);
            }

            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            Task task = null;

            switch (taskType) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                if (parts.length < 4) {
                    throw new InvalidTaskException("Deadline missing for: " + line);
                }
                LocalDateTime deadline = DateTimeUtility.parse(parts[3]);
                if (deadline != null) {
                    task = new Deadline(description, deadline);
                }
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

                LocalDateTime startDate = DateTimeUtility.parse(dateParts[0]);
                LocalDateTime endDate = DateTimeUtility.parse(dateParts[1]);
                if (startDate != null && endDate != null) {
                    task = new Event(description, startDate, endDate);
                }
                break;
            default:
                throw new InvalidTaskException("Invalid task type for: " + line);
            }
            if (task != null) {
                task.setDone(isDone);
            }
            return task;
        } catch (InvalidTaskException | DateTimeParseException | InvalidDateException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

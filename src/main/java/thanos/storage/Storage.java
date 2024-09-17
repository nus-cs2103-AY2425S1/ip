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
 * Implements the {@code IStorage} interface to manage reading from and writing to a file,
 * ensuring that task data is preserved across application runs.
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
     *
     * @return an {@code ArrayList} of {@code Task} objects read from the file.
     */
    @Override
    public ArrayList<Task> load() {
        ensureFileExists();
        return getTasksFromFile();
    }

    /**
     * Reads tasks from a file and converts them into a list of {@code Task} objects.
     *
     * @return an {@code ArrayList} of {@code Task} objects loaded from the file. If no tasks
     *         are found or the file does not exist, an empty list is returned.
     */
    private ArrayList<Task> getTasksFromFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = convertStringToTask(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No data file found");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return taskList;
    }

    /**
     * Saves the given list of tasks to a file.
     *
     * @param taskList the {@code ArrayList} of {@code Task} objects to be saved to the file.
     */
    @Override
    public void save(ArrayList<Task> taskList) {
        ensureFileExists();
        saveTasksToFile(taskList);
    }

    /**
     * Helper function to save a list of tasks to a file.
     *
     * @param taskList the {@code ArrayList} of {@code Task} objects to be saved to the file.
     */
    private void saveTasksToFile(ArrayList<Task> taskList) {
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
     * Ensures that the file exists and creates parent directories or a new file if necessary.
     */
    private void ensureFileExists() {
        try {
            if (file.exists()) {
                return;
            }

            if (!this.file.getParentFile().mkdirs()) {
                throw new IOException("Failed to create the necessary directories for the file");
            }
            if (!this.file.createNewFile()) {
                throw new IOException("Failed to create file");
            }
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
    }

    /**
     * Converts a string representation of a task to a {@code Task} object.
     *
     * @param line the string representation of the task from the file.
     * @return a {@code Task} object if the string is valid; {@code null} otherwise.
     */
    private Task convertStringToTask(String line) {
        try {
            String[] parts = getParts(line);
            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task = createTask(line, taskType, description, parts);
            if (task != null) {
                task.setDone(isDone);
            }
            return task;
        } catch (InvalidTaskException | DateTimeParseException | InvalidDateException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Splits the provided string into parts based on the delimiter " | " and returns an array of these parts.
     *
     * @param line the string to be split, representing a line of task data.
     * @return a string array containing the parts of the input line.
     * @throws InvalidTaskException if the line contains fewer than three parts, indicating missing data.
     */
    private static String[] getParts(String line) throws InvalidTaskException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new InvalidTaskException("Missing data in:" + line);
        }
        return parts;
    }

    /**
     * Creates a task based on the provided parameters.
     *
     *
     * @param line the raw input line used to create the task, which is used for error messages.
     * @param taskType the type of task to create. This should be "T" for Todo, "D" for Deadline, or "E" for Event.
     * @param description the description of the task.
     * @param parts an array of strings containing additional details required for creating the task.
     *              For Deadline and Event tasks, this should include a date or date range in the correct format.
     *
     * @return the created Task object, which can be a Todo, Deadline, or Event.
     *
     * @throws InvalidTaskException if the task type is invalid, or if required parts are missing.
     * @throws InvalidDateException if the date format is invalid when parsing the date or date range.
     */
    private static Task createTask(String line, String taskType, String description, String[] parts)
            throws InvalidTaskException, InvalidDateException {
        Task task = null;
        switch (taskType) {
        case "T":
            task = createTodo(description);
            break;
        case "D":
            task = createDeadline(line, description, parts);
            break;
        case "E":
            task = createEvent(line, description, parts);
            break;
        default:
            throw new InvalidTaskException("Invalid task type for: " + line);
        }
        return task;
    }

    /**
     * Creates a new {@code Todo} instance with the specified description.
     *
     * @param description the description of the todo task.
     * @return a {@code Todo} instance with the given description.
     */
    private static Todo createTodo(String description) {
        return new Todo(description);
    }

    /**
     * Creates a new {@code Deadline} instance based on the provided line, description, and parts of the line.
     *
     * @param line the original line of task data from which the deadline will be extracted.
     * @param description the description of the deadline task.
     * @param parts an array of string parts extracted from the line, which should include the deadline.
     * @return a {@code Deadline} instance with the specified description and deadline.
     * @throws InvalidTaskException if the parts array does not contain sufficient data.
     * @throws InvalidDateException if the deadline cannot be parsed into a {@code LocalDateTime} object.
     */
    private static Task createDeadline(String line, String description, String[] parts)
            throws InvalidTaskException, InvalidDateException {
        Task task;
        if (parts.length < 4) {
            throw new InvalidTaskException("Deadline missing for: " + line);
        }
        LocalDateTime deadline = DateTimeUtility.parse(parts[3]);
        task = new Deadline(description, deadline);
        return task;
    }

    /**
     * Creates a new {@code Event} instance based on the provided line, description, and parts of the line.
     *
     * @param line the original line of task data from which the event date will be extracted.
     * @param description the description of the event task.
     * @param parts an array of string parts extracted from the line, which should include the start and end dates.
     * @return an {@code Event} instance with the specified description, start date, and end date.
     * @throws InvalidTaskException if the parts array does not contain sufficient data.
     * @throws InvalidDateException if the start or end date cannot be parsed into {@code LocalDateTime} objects.
     */
    private static Task createEvent(String line, String description, String[] parts)
            throws InvalidTaskException, InvalidDateException {
        Task task;
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
        task = new Event(description, startDate, endDate);
        return task;
    }
}

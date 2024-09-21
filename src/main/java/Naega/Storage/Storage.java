package Naega.Storage;

import Naega.NaegaException;
import Naega.Task.Deadline;
import Naega.Task.Event;
import Naega.Task.Task;
import Naega.Task.Todo;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Handles loading and saving tasks to and from a file.
 */
public class Storage {

    private final String filePath;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath the path to the file where tasks are stored
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path must not be null or empty";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     *
     * @return an ArrayList of tasks loaded from the file
     * @throws NaegaException if an error occurs while loading tasks
     */
    public ArrayList<Task> load() throws NaegaException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] taskDetails = line.split(" \\| ");
                assert taskDetails.length >= 3 : "Invalid task format in file";
                Task task = parseTask(taskDetails);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            // No file exists yet, so start with an empty list
        } catch (IOException e) {
            throw new NaegaException("Error reading from file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Parses a task from an array of task details.
     *
     * @param taskDetails an array containing the details of the task
     * @return the parsed Task object
     * @throws NaegaException if the task details are invalid
     */
    private Task parseTask(String[] taskDetails) throws NaegaException {
        assert taskDetails.length >= 3 : "Insufficient task details for parsing";
        System.out.println("taskDetails length: " + taskDetails.length);
        System.out.println("taskDetails: " + Arrays.toString(taskDetails));

        String taskType = taskDetails[0];
        String description = taskDetails[2];

        switch (taskType) {
            case "T":
                return new Todo(description);
            case "D":
                assert taskDetails.length >= 4 : "Insufficient details for Deadline task";
                LocalDateTime deadline = LocalDateTime.parse(taskDetails[3], FORMATTER);
                return new Deadline(description, deadline);
            case "E":
                assert taskDetails.length >= 5 : "Insufficient details for Event task";
                LocalDateTime eventStart = LocalDateTime.parse(taskDetails[3], FORMATTER);
                LocalDateTime eventEnd = LocalDateTime.parse(taskDetails[4], FORMATTER);
                assert eventStart.isBefore(eventEnd) : "'from' date must be before 'to' date for Event";
                return new Event(description, eventStart, eventEnd);
            default:
                throw new NaegaException("Invalid task type in file.");
        }
    }

    /**
     * Saves the list of tasks to the file specified by the file path.
     *
     * @param tasks the list of tasks to save
     */
    public void save(ArrayList<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            assert tasks != null : "Task list must not be null when saving";
            for (Task task : tasks) {
                assert task != null : "Task must not be null when saving";
                writer.println(task.toSaveFormat());
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public boolean fileExists() {
        File file = new File(filePath);
        System.out.println("Checking if file exists: " + file.getAbsolutePath());  // Debugging
        return file.exists();
    }
}
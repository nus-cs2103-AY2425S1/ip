package evan.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import evan.exception.FileCreationException;
import evan.exception.LoadingException;
import evan.exception.SavingException;
import evan.task.Deadline;
import evan.task.Event;
import evan.task.Task;
import evan.task.Todo;


/**
 * Represents the storage that the chatbot saves data to.
 */
public class Storage {
    private final Path filePath;

    /**
     * Instantiates a Storage object.
     * Creates a .txt file at the specified file path if it doesn't exist.
     *
     * @param filePath File path of the .txt file that will store the chatbot's data.
     * @throws FileCreationException If there is an error creating the .txt file.
     */
    public Storage(String filePath) throws FileCreationException {
        this.filePath = Path.of(filePath);

        try {
            // Create the file if it doesn't exist
            if (!Files.exists(this.filePath)) {
                Files.createFile(this.filePath);
            }
        } catch (IOException e) {
            throw new FileCreationException(filePath);
        }
    }

    /**
     * Converts the string representation of tasks found in the .txt file into a Task object.
     * The ExtractMethodRecommender warning is suppressed because all the logic for decoding the task from its string
     * representation in the .txt file should be in the same method.
     *
     * @param s The string representation of a task found in the .txt file.
     * @return Task object.
     * @throws LoadingException If there is an error reading a string representation of a task found in the .txt file.
     */
    @SuppressWarnings("ExtractMethodRecommender")
    private static Task decodeTask(String s) throws LoadingException {
        // Split each line by the "|" character
        String[] parts = s.split("\\|");

        // Trim leading and trailing whitespace in all parts
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }

        try {
            String taskType = parts[0]; // "T", "D", or "E"
            String taskStatus = parts[1]; // "1" or "0"
            String description = parts[2];

            Task task;
            switch (taskType) {
            case "T" -> task = new Todo(description);
            case "D" -> {
                String by = parts[3];
                task = new Deadline(description, by);
            }
            case "E" -> {
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
            }
            default -> throw new LoadingException("Invalid task type.\nLine that caused the error: '" + s + "'");
            }

            switch (taskStatus) {
            case "0" -> task.markAsUndone();
            case "1" -> task.markAsDone();
            default -> throw new LoadingException("Invalid task status.\nLine that caused the error: '" + s + "'");
            }

            return task;

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new LoadingException("Invalid number of arguments for task type.\nLine that caused the error: '"
                    + s + "'");
        }
    }

    /**
     * Returns an ArrayList of Tasks containing tasks decoded from the storage .txt file.
     *
     * @return ArrayList of Tasks containing tasks decoded from the storage .txt file.
     * @throws LoadingException If there is an error reading the storage .txt file.
     */
    public ArrayList<Task> load() throws LoadingException {
        ArrayList<Task> taskList = new ArrayList<>(); // Create the ArrayList that will eventually be returned

        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                Task taskToAdd = decodeTask(line);
                taskList.add(taskToAdd);
            }
        } catch (IOException e) {
            throw new LoadingException("Error occurred while reading the file.");
        }

        return taskList;
    }

    /**
     * Writes the chatbot's current data to the storage .txt file.
     * This includes saving all the current tasks to the .txt file.
     *
     * @param taskList TaskList that the chatbot is using.
     * @throws SavingException If there is an error when writing to the .txt file.
     */
    public void save(TaskList taskList) throws SavingException {
        try {
            String data = taskList.encodeAsString();
            Files.writeString(filePath, data);
        } catch (IOException e) {
            throw new SavingException("Error occurred while saving to the file.");
        }
    }
}

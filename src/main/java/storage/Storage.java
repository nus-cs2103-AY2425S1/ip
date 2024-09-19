package storage;

import exception.FileNotFoundKukiShinobuException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import task.tags.Tags;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Manages the storage and retrieval of tasks in a file.
 * <p>
 * The {@code Storage} class provides functionality to load tasks from a file and
 * write tasks to a file. It handles reading and parsing the file contents into
 * task objects, as well as serializing task objects into a format suitable for
 * file storage.
 * </p>
 */
public class Storage {
    private static final String DELIMITER = " \\| ";

    private final String filePath;

    /**
     * Constructs a {@code Storage} instance with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     * <p>
     * This method reads the file contents, parses the tasks, and returns a list
     * of tasks. If the file does not exist or an error occurs while accessing
     * the file, a {@link FileNotFoundKukiShinobuException} is thrown.
     * </p>
     *
     * @return An {@code ArrayList} of tasks loaded from the file.
     * @throws FileNotFoundKukiShinobuException If the file is not found or cannot
     *                                          be accessed.
     */
    public ArrayList<Task> load() throws FileNotFoundKukiShinobuException {
        // Step 1: Read the file content
        String fileContent = readFileContent();

        // Step 2: Parse the tasks from the file content
        return parseTasks(fileContent);
    }

    /**
     * Reads the file content from the specified file path.
     *
     * @return A String containing the file content.
     * @throws FileNotFoundKukiShinobuException If the file is not found or cannot
     *                                          be accessed.
     */
    private String readFileContent() throws FileNotFoundKukiShinobuException {
        File file = new File(this.filePath);
        // Ensure parent directory exists
        file.getParentFile().mkdirs();
        StringBuilder content = new StringBuilder();
        try {
            if (!file.exists()) {
                // Create an empty file
                file.createNewFile();
            }
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    content.append(scanner.nextLine()).append(System.lineSeparator());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
            throw new FileNotFoundKukiShinobuException();
        } catch (IOException e) {
            System.err.println("Error creating or accessing the file: " + filePath);
            e.printStackTrace();
            throw new FileNotFoundKukiShinobuException();
        }
        return content.toString();
    }

    /**
     * Parses tasks from the given file content.
     *
     * @param fileContent The content of the file as a String.
     * @return An {@code ArrayList} of tasks parsed from the file content.
     */
    private ArrayList<Task> parseTasks(String fileContent) {
        ArrayList<Task> existingTasks = new ArrayList<>();
        // If the file is empty, return immediately
        if (fileContent.isEmpty()) {
            return existingTasks;
        }
        String[] lines = fileContent.split(System.lineSeparator());
        for (String line : lines) {
            Task task = parseTask(line);
            if (task != null) {
                existingTasks.add(task);
            }
        }
        return existingTasks;
    }

    /**
     * Parses a single line of text to create a Task object.
     *
     * @param taskString A single line of text representing a task.
     * @return A Task object created from the line of text, or null if the line is invalid.
     */
    private Task parseTask(String taskString) {
        String[] taskConstituents = taskString.split(DELIMITER);
        if (taskConstituents.length < 2) {
            System.err.println("Invalid task format: " + taskString);
            return null; // Skip invalid lines
        }

        String taskType = taskConstituents[0];
        boolean isCompleted = taskConstituents[1].equals("1");

        // Extract and parse tags if present
        HashSet<String> tags = new HashSet<>();
        if (taskConstituents.length > 2) {
            String tagsString = taskConstituents[taskConstituents.length - 1];
            tags = Tags.parseDatabaseString(tagsString);
        }

        switch (taskType) {
            case "T":
                return parseTodo(taskConstituents, isCompleted, tags);
            case "D":
                return parseDeadline(taskConstituents, isCompleted, tags);
            case "E":
                return parseEvent(taskConstituents, isCompleted, tags);
            default:
                System.err.println("Unknown task type: " + taskType);
                return null;
        }
    }

    /**
     * Parses a Todo task from the constituents array.
     *
     * @param taskConstituents The array of task constituents.
     * @param isCompleted      The completion status of the task.
     * @param tags             The tags associated with the task.
     * @return A Todo object or null if the line is invalid.
     */
    private Task parseTodo(String[] taskConstituents, boolean isCompleted, HashSet<String> tags) {
        if (taskConstituents.length >= 3) {
            return new Todo(taskConstituents[2], isCompleted, tags);
        } else {
            System.err.println("Invalid task.Todo task format: " + String.join(DELIMITER, taskConstituents));
            return null;
        }
    }

    /**
     * Parses a Deadline task from the constituents array.
     *
     * @param taskConstituents The array of task constituents.
     * @param isCompleted      The completion status of the task.
     * @param tags             The tags associated with the task.
     * @return A Deadline object or null if the line is invalid.
     */
    private Task parseDeadline(String[] taskConstituents, boolean isCompleted, HashSet<String> tags) {
        if (taskConstituents.length >= 4) {
            return new Deadline(taskConstituents[2], taskConstituents[3], isCompleted, tags);
        } else {
            System.err.println("Invalid task.Deadline task format: " + String.join(DELIMITER, taskConstituents));
            return null;
        }
    }

    /**
     * Parses an Event task from the constituents array.
     *
     * @param taskConstituents The array of task constituents.
     * @param isCompleted      The completion status of the task.
     * @param tags             The tags associated with the task.
     * @return An Event object or null if the line is invalid.
     */
    private Task parseEvent(String[] taskConstituents, boolean isCompleted, HashSet<String> tags) {
        if (taskConstituents.length >= 5) {
            return new Event(taskConstituents[2], taskConstituents[3], taskConstituents[4], isCompleted, tags);
        } else {
            System.err.println("Invalid task.Event task format: " + String.join(DELIMITER, taskConstituents));
            return null;
        }
    }


    /**
     * Writes the specified list of tasks to the file.
     * <p>
     * This method serializes the tasks into a format suitable for storage and writes
     * them to the file specified by the file path.
     * </p>
     *
     * @param tasks An {@code ArrayList} of tasks to be written to the file.
     */
    public void write(ArrayList<Task> tasks) {
        // Step 1: Convert tasks to a String representation
        String tasksString = convertTasksToString(tasks);

        // Step 2: Write the String representation to the file
        writeStringToFile(tasksString);
    }

    /**
     * Converts a list of tasks to a String representation.
     *
     * @param tasks An {@code ArrayList} of tasks to be converted.
     * @return A String representation of the tasks.
     */
    private String convertTasksToString(ArrayList<Task> tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            String theString = task.getDatabaseString();
            stringBuilder.append(task.getDatabaseString()).append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    /**
     * Writes a String representation to the file specified by the file path.
     *
     * @param content The content to be written to the file.
     */
    private void writeStringToFile(String content) {
        try (FileWriter fileWriter = new FileWriter(this.filePath)) {
            fileWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

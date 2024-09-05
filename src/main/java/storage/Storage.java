package storage;

import exception.FileNotFoundKukiShinobuException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
        // Step 1: Read the file, and throw an error is the file can't be found
        File file = new File(this.filePath);
        // Ensure parent directory exists
        file.getParentFile().mkdirs();
        ArrayList<String> input = new ArrayList<>();
        try {
            System.out.println(file.exists());
            // Check if the file exists
            if (!file.exists()) {
                // Create an empty file
                file.createNewFile();
            }
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    input.add(scanner.nextLine());
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

        // Step 2: Parse the tasks
        ArrayList<Task> existingTasks = new ArrayList<>();
        for (String taskString : input) {
            String[] taskConstituents = taskString.split(DELIMITER);
            if (taskConstituents.length < 2) {
                System.err.println("Invalid task format: " + taskString);
                continue; // Skip to the next line
            }

            String taskType = taskConstituents[0];
            boolean isCompleted = taskConstituents[1].equals("1");

            switch (taskType) {
                case "T":
                    if (taskConstituents.length >= 3) {
                        existingTasks.add(new Todo(taskConstituents[2], isCompleted));
                    } else {
                        System.err.println("Invalid task.Todo task format: " + taskString);
                    }
                    break;
                case "D":
                    if (taskConstituents.length >= 4) {
                        existingTasks.add(new Deadline(taskConstituents[2], taskConstituents[3], isCompleted));
                    } else {
                        System.err.println("Invalid task.Deadline task format: " + taskString);
                    }
                    break;
                case "E":
                    if (taskConstituents.length >= 5) {
                        existingTasks.add(new Event(taskConstituents[2], taskConstituents[3], taskConstituents[4], isCompleted));
                    } else {
                        System.err.println("Invalid task.Event task format: " + taskString);
                    }
                    break;
                default:
                    System.err.println("Unknown task type: " + taskType);
                    break;
            }
        }
        return existingTasks;
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
        // TODO: Takes this.tasks and write it to the database file
        StringBuilder stringToBeWritten = new StringBuilder();
        // Step 1: Parse the file into a single string
        for (Task task : tasks) {
            stringToBeWritten.append(task.getDatabaseString()).append(System.lineSeparator());
        }

        // Step 2: Insert the entire text string into the file
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(this.filePath);
            fileWriter.write(stringToBeWritten.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

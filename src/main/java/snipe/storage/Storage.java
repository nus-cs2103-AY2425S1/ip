package snipe.storage;

import snipe.core.TaskList;
import snipe.exception.SnipeException;
import snipe.task.Deadline;
import snipe.task.Event;
import snipe.task.Task;
import snipe.task.ToDo;

import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * The {@code Storage} class handles the reading and writing of a user's task list to and from a file.
 * It is responsible for loading tasks from the file when the application starts and saving tasks back to the file when the application exits or tasks are modified.
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     *
     * @param filePath The path to the file where the task list is stored.
     */
    public Storage(String filePath) {
        // Assert that the provided file path is not null
        assert filePath != null : "File path must not be null";
        this.filePath = Paths.get(filePath);
    }

    /**
     * Ensures the directory and file exist, creating them if necessary.
     *
     * @throws IOException If an input or output error occurs while creating the directory or file.
     */
    public void initialise() throws IOException {
        Path directoryPath = filePath.getParent();
        if (Files.notExists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }
    }

    /**
     * Reads the task list from the file and returns it as an {@link ArrayList} of {@link Task} objects.
     *
     * @return The list of tasks read from the file.
     * @throws SnipeException If the storage file is not initialised.
     * @throws IOException    If an input or output error occurs while reading the file.
     */
    public ArrayList<Task> readTaskList() throws SnipeException, IOException {
        // Assert that the file path exists before reading the file
        assert Files.exists(filePath) : "The file path should exist before reading tasks";

        if (Files.exists(filePath)) {
            ArrayList<Task> taskList = new ArrayList<Task>();
            try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Task task = parseTaskFromString(line);

                    // Assert that the task read from the file is valid
                    assert task != null : "Task read from file should not be null";


                    taskList.add(task);
                }
            }
            return taskList;
        } else {
            throw new SnipeException("snipe.storage.Storage not initialised");
        }
    }

    /**
     * Parses a task from a string representation stored in the file.
     *
     * @param line The line from the file representing a task.
     * @return The parsed {@link Task} object or {@code null} if parsing fails.
     */
    private Task parseTaskFromString(String line) {
        try {
            String[] split = line.split(" \\| ");
            String taskType = split[0];
            String taskDescription = split[2];
            Task task = null;
            switch (taskType) {
            case "T":
                task = new ToDo(taskDescription);
                break;
            case "D":
                task = new Deadline(taskDescription, split[3]);
                break;
            case "E":
                task = new Event(taskDescription, split[3], split[4]);
                break;
            }

            // Assert that the task is valid (not null)
            assert task != null : "Parsed task must not be null";

            if (split[1].equals("1")) {
                task.changeStatus();
            }
            return task;
        } catch (Exception e) {
            System.out.print("String parsed is invalid: " + e.getMessage());
            return null;
        }
    }

    /**
     * Saves the current task list to the file.
     *
     * @param tasks The task list to be saved.
     * @throws IOException If an input or output error occurs while writing to the file.
     */
    public void saveTaskList(TaskList tasks) throws IOException {
        initialise();
        ArrayList<Task> taskList = tasks.getTasks();

        // Assert that the task list is not null
        assert taskList != null : "Task list to be saved must not be null";

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)){
            for (Task task : taskList) {
                // Assert that each task in the task list is not null
                assert task != null : "Task in task list must not be null";

                String item = task.parseToFileFormat();
                writer.write(item);
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("snipe.task.Task is not a valid task");
        }
    }
}

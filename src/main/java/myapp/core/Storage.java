package myapp.core;


import myapp.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The {@code Storage} class handles the loading and saving of tasks to and from a file.
 * It provides methods to read tasks from a file into a {@link TaskList} and save tasks
 * from a {@link TaskList} to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     *
     * @param filePath the file path where tasks will be loaded from and saved to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file.
     *
     * @return a list of {@link Task} objects loaded from the file.
     * @throws BingBongException if an error occurs while reading from the file
     * or if the file contains unknown task types.
     */
    public List<Task> load() throws BingBongException {
        List<Task> tasks = new ArrayList<>();

        try (Scanner reader = new Scanner(new File(filePath))) {
            String line;
            while (reader.hasNext()) {
                line = reader.nextLine();
                String[] taskData = line.split(" \\| ");
                String type = taskData[0];
                boolean isDone = taskData[1].equals("1");
                String description = taskData[2];
                Task task;
                switch (type) {
                case "T":
                    task = new ToDo(description);
                    break;
                case "D":
                    String by = taskData[3];
                    task = new Deadline(description, DateTimeHandler.parse(by));
                    break;
                case "E":
                    String from = taskData[3];
                    String to = taskData[4];
                    task = new Event(description, DateTimeHandler.parse(from), DateTimeHandler.parse(to));
                    break;
                default:
                    throw new BingBongException("Unknown task type in file.");
                }
                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new BingBongException("Error reading from file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the tasks in the given {@link TaskList} to the specified file.
     *
     * @param tasks the {@link TaskList} containing the tasks to be saved.
     * @throws BingBongException if an error occurs while writing to the file or if the directory cannot be created.
     */
    public void save(TaskList tasks) throws BingBongException {
        File file = new File(filePath);

        // Ensure the parent directory exists
        File directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            if (!directory.mkdirs()) {
                throw new BingBongException("Failed to create directory: " + directory);
            }
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new BingBongException("Error writing to file: " + e.getMessage());
        }

    }
}

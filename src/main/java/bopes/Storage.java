package bopes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import bopes.exception.BopesException;
import bopes.task.Task;
import bopes.task.TaskList;

/**
 * The Storage class handles the reading and writing of task data to and from a file.
 * It is responsible for loading tasks from a file into a TaskList and saving tasks from a TaskList to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a new Storage object with the specified file path.
     *
     * @param filePath the path to the file where tasks will be stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     * If the file does not exist, it returns an empty list of tasks.
     *
     * @return an ArrayList of Task objects loaded from the file
     * @throws BopesException if there is an error loading tasks from the file
     */
    public ArrayList<Task> loadTasks() throws BopesException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks; // Return an empty list if the file does not exist
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String taskData = scanner.nextLine();
                Task task = Parser.parseTask(taskData);
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new BopesException("Error loading tasks from file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the tasks from the given TaskList to the file specified by the file path.
     * It ensures that the directory exists before attempting to save the file.
     *
     * @param tasks the TaskList containing the tasks to be saved
     * @throws BopesException if there is an error saving tasks to the file
     */
    public void saveTasks(TaskList tasks) throws BopesException {
        // Ensure the directory exists before trying to save
        File directory = new File(filePath).getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks.getTasks()) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new BopesException("Error saving tasks to file: " + e.getMessage());
        }
    }
}

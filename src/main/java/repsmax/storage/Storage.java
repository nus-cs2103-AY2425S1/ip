package repsmax.storage;

import repsmax.logic.TaskList;
import repsmax.model.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles the saving and loading of tasks from a file.
 * <p>
 * The {@code Storage} class allows the application to persist tasks to a file
 * and load them when needed. The file path is provided upon construction of the
 * {@code Storage} object.
 * </p>
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path for task persistence.
     *
     * @param filePath The path to the file where tasks will be saved or loaded from.
     * @throws IllegalArgumentException If the file path is null or empty.
     */
    public Storage(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty.");
        }
        this.filePath = filePath;
    }

    /**
     * Saves the tasks in the provided {@code TaskList} to the file.
     * <p>
     * Each task is written in the format defined by the {@code Task}'s {@code toFileFormat()}
     * method. Tasks are written line by line.
     * </p>
     *
     * @param tasks The {@code TaskList} containing tasks to be saved.
     */
    public void save(TaskList tasks) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            for (Task task : tasks.getTasks()) {
                fileWriter.write(task.toFileFormat() + System.lineSeparator());
            }
            System.out.println("Tasks successfully saved to file.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file and adds them to the provided {@code TaskList}.
     * <p>
     * If the file does not exist, a new task list will be started. Each line in the file
     * is parsed into a {@code Task} object using {@code Task}'s {@code fromFileFormat()} method.
     * </p>
     *
     * @param tasks The {@code TaskList} to which loaded tasks will be added.
     */
    public void load(TaskList tasks) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("No task file found. Starting a new task list.");
            return;
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                tasks.add(Task.fromFileFormat(line));
            }
            System.out.println("Tasks successfully loaded from file.");
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks: " + e.getMessage());
        }
    }
}


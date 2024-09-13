package shenhe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import shenhe.parser.Parser;
import shenhe.task.Task;

/**
 * Handles loading and saving tasks from/to a file.
 * <p>
 * The {@code Storage} class provides functionality to read tasks from a specified file
 * and write tasks back to the file. It manages the file operations needed to persist
 * tasks between application runs.
 * </p>
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     * <p>
     * Initializes the {@code Storage} instance to work with the given file path for
     * storing and retrieving tasks.
     * </p>
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified in the constructor.
     * <p>
     * Reads the file line by line, parsing each line to create {@code Task} objects
     * and adding them to a {@code TaskList}. If the file does not exist, an empty
     * {@code TaskList} is returned. Corrupted lines in the file are ignored with
     * a warning message.
     * </p>
     *
     * @return A {@code TaskList} containing the tasks read from the file.
     */
    public TaskList loadTasks() {
        TaskList tasks = new TaskList();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks; // Return empty list if file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Task task = Parser.parseCommandInFile(line);
                    tasks.addTask(task);
                } catch (Exception e) {
                    System.out.println("Warning: Corrupted line ignored.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the given tasks to the file specified in the constructor.
     * <p>
     * Writes each task from the {@code TaskList} to the file, formatting each task
     * using its {@code toFileFormat} method. Creates the necessary directories if they
     * do not exist. If an {@code IOException} occurs during writing, an error message
     * is printed.
     * </p>
     *
     * @param tasks The {@code TaskList} containing the tasks to be saved.
     */
    public void saveTasks(TaskList tasks) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs(); // Create directory if it doesn't exist
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (int i = 0; i < tasks.getSize(); i++) {
                    writer.write(tasks.getTask(i).toFileFormat() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}

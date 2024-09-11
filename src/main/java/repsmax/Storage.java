package repsmax;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Manages the saving and loading of tasks to and from a file.
 * <p>
 * The {@code Storage} class provides methods to save tasks to a file and
 * load tasks from a file. The file path is specified during the creation
 * of the {@code Storage} object.
 * </p>
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     *
     * @param filePath the path to the file where tasks will be saved or loaded.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path cannot be null or empty";
        this.filePath = filePath;
    }

    /**
     * Saves the tasks to the file specified by the file path.
     * <p>
     * Each task is written to the file in a format defined by the {@code Task}
     * class's {@code toFileFormat()} method.
     * </p>
     *
     * @param tasks the {@code TaskList} containing the tasks to be saved.
     */
    public void save(TaskList tasks) {
        try {
            File file = new File(filePath);
            assert file.exists() || file.createNewFile() : "Failed to create new file";
            FileWriter fileWriter = new FileWriter(file);
            for (Task task : tasks.getTasks()) {
                fileWriter.write(task.toFileFormat() + "\n");
            }
            fileWriter.close();
            System.out.println("Tasks saved to file.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
        }
    }

    /**
     * Loads tasks from the file specified by the file path.
     * <p>
     * If the file does not exist, a message is printed indicating that
     * a new list will be started. Each line in the file is parsed using
     * the {@code Task} class's {@code fromFileFormat()} method to create
     * tasks that are added to the provided {@code TaskList}.
     * </p>
     *
     * @param tasks the {@code TaskList} to which loaded tasks will be added.
     */
    public void load(TaskList tasks) {
        try {
            File file = new File(filePath);
            assert file.exists() : "File does not exist";
            if (!file.exists()) {
                System.out.println("No task found. Starting a new List");
                return;
            }
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                tasks.add(Task.fromFileFormat(line));
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks.");
        }
    }
}

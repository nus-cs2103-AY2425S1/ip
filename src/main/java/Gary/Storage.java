package Gary;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Gary.task.Task;

/**
 * The {@code Storage} class handles loading and saving tasks to and from a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     *
     * @param filePath The path of the file to store tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task list from the file specified by {@code filePath}.
     * If the file or its parent directory does not exist, they are created.
     *
     * @return A {@code Scanner} object to read the file.
     * @throws FileNotFoundException If the file could not be found or created.
     */
    public Scanner loadTaskList() throws FileNotFoundException {
        File file = new File(this.filePath);
        createDirectoryIfNotExists(file.getParentFile());
        createFileIfNotExists(file);

        return new Scanner(file);
    }

    /**
     * Creates the directory if it does not exist.
     *
     * @param directory The directory to create.
     */
    private void createDirectoryIfNotExists(File directory) {
        if (directory != null && !directory.exists()) {
            if (!directory.mkdirs()) {
                System.err.println("Failed to create directory: " + directory);
            }
        }
    }

    /**
     * Creates the file if it does not exist.
     *
     * @param file The file to create.
     */
    private void createFileIfNotExists(File file) {
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    System.err.println("Failed to create file: " + file);
                }
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
            }
        }
    }

    /**
     * Saves the given {@code TaskList} to the file specified by {@code filePath}.
     * Each task in the list is written to the file on a new line.
     *
     * @param taskList The {@code TaskList} object containing tasks to be saved.
     * @throws IOException If an I/O error occurs during writing.
     */
    public void saveTask(TaskList taskList) throws IOException {
        File file = new File(filePath);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.getTask(i);
                bufferedWriter.write(task.parseToFile());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks to file: " + e.getMessage());
            throw e; // rethrow the exception to indicate failure
        }
    }
}


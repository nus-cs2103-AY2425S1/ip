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
    // Path to the file where tasks are stored
    private final String filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     *
     * @param filePath The path of the file to store tasks.
     */
    public Storage(String filePath) {
        // Assertion: Ensure that the file path is not null
        assert filePath != null : "File path cannot be null";

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
        File directory = new File("src/textFile");

        if (!directory.exists()) {
            // Assertion: Ensure that directory creation is successful
            boolean dirCreated = directory.mkdir();
            assert dirCreated : "Failed to create directory";
        }

        if (!file.exists()) {
            try {
                // Create the file if it does not exist
                boolean fileCreated = file.createNewFile();
                // Assertion: Ensure that the file creation is successful
                assert fileCreated : "Failed to create file";
            } catch (IOException e) {
                // Assertion: Ensure that no IOException occurs during file creation
                assert false : "IOException occurred while creating file: " + e.getMessage();
            }
        }

        // Assertion: Ensure the file exists after attempting to create it
        assert file.exists() : "File should exist after creation attempt";

        return new Scanner(file);
    }

    /**
     * Saves the given {@code TaskList} to the file specified by {@code filePath}.
     * Each task in the list is written to the file on a new line.
     *
     * @param taskList The {@code TaskList} object containing tasks to be saved.
     * @throws IOException If an I/O error occurs during writing.
     */
    public void saveTask(TaskList taskList) throws IOException {
        // Assertion: Ensure the taskList is not null
        assert taskList != null : "TaskList cannot be null";

        File file = new File(filePath);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.getTask(i);

            // Assertion: Ensure task is not null before writing
            assert task != null : "Task cannot be null when saving to file";

            bufferedWriter.write(task.parseToFile());
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}


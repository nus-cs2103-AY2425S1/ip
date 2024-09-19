package rex.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import rex.task.Task;
import rex.task.TaskList;

/**
 * The {@code Storage} class handles the management of persistent storage for tasks.
 * It is responsible for loading tasks from a file into a {@code TaskList} and updating
 * the file with the current state of the {@code TaskList}.
 */
public class Storage {
    private final String filePath;
    private final String tempPath;

    public Storage(String filePath, String tempPath) {
        this.filePath = filePath;
        this.tempPath = tempPath;
    }

    /**
     * Loads tasks from the file specified by {@code filepath} into the provided {@code TaskList}.
     * This method ensures that the necessary directories and files exist before parsing.
     *
     * @param list The {@code TaskList} instance to which tasks will be loaded.
     * @throws IOException If an I/O error occurs while reading the file or creating directories/files.
     */
    public void loadFile(TaskList list) throws IOException {
        File file = new File(filePath);
        File dir = file.getParentFile();

        createDirectory(dir);
        createFile(file);

        // Parse file data into the provided TaskList
        Parser.parseFile(list, file);
    }

    /**
     * Updates the file specified by {@code filepath} with the current state of the {@code TaskList}.
     * This method first writes the tasks to a temporary file, then replaces the main file with the temporary one.
     *
     * @param list The {@code TaskList} instance containing the tasks to be saved.
     * @throws IOException If an I/O error occurs while writing to or copying files.
     */
    public void updateFile(TaskList list) throws IOException {
        File temp = new File(tempPath);

        // Creates temp file to copy taskList from
        Path tempFilePath = Paths.get(tempPath);
        Files.deleteIfExists(tempFilePath);
        createFile(temp);

        try (FileWriter writer = new FileWriter(temp, true)) {
            for (int i = 1; i <= list.size(); i++) {
                Task task = list.getTask(i);
                writer.write(task.formatter() + System.lineSeparator());
            }
        }

        // Replace the main file with the updated temp file
        Files.copy(tempFilePath, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(tempFilePath);
    }

    /**
     * Creates the specified directory if it does not already exist.
     *
     * @param dir The directory to be created.
     */
    private void createDirectory(File dir) {
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }
    }

    /**
     * Creates the specified file if it does not already exist.
     *
     * @param f The file to be created.
     * @throws IOException If an I/O error occurs while creating the file.
     */
    private void createFile(File f) throws IOException {
        if (!f.exists()) {
            f.createNewFile();
        }
    }
}

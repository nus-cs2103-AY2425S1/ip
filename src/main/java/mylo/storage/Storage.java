package mylo.storage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import mylo.task.Task;
import mylo.task.TaskList;
import mylo.utils.exceptions.IllegalValueException;

/**
 * Handles the storage of tasks in a file.
 * <p></p>
 * <p>This class is responsible for saving tasks to a file, loading tasks from the file,
 * and rewriting the file with updated task data.</p>
 *
 * @author cweijin
 */
public class Storage {
    /**
     * File path to store task list.
     */
    public static final String STORAGE_FILEPATH = "data/tasks.txt";

    /**
     * Locate to the file that stores task list.
     */
    private final Path path = Paths.get(STORAGE_FILEPATH);

    /**
     * Saves the specified {@code task} data to the storage file.
     *
     * @param task The task instance containing data to be stored in the storage file.
     * @throws StorageOperationException if there are errors converting and/or storing data to the file.
     */
    public void save(Task task) throws StorageOperationException {
        try (FileWriter fileWriter = new FileWriter(STORAGE_FILEPATH, true)) {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            fileWriter.write(task.storageFormat() + System.lineSeparator());
        } catch (IOException e) {
            throw new StorageOperationException("Error writing to file: " + path);
        }
    }

    /**
     * Loads the {@code TaskList} data from the storage file and returns it.
     * Returns an empty {@code TaskList} if the file does not exist or is not a regular file.
     *
     * @return A {@code TaskList} containing tasks loaded from the storage file.
     * @throws StorageOperationException if there are errors reading and/or converting data from the file.
     * @throws FileCorruptedException if the file content is not in the expected format.
     */
    public TaskList load() throws StorageOperationException, FileCorruptedException {

        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return new TaskList();
        }

        try {
            return TaskList.decodeTxt(Files.readAllLines(path));
        } catch (IOException e) {
            throw new StorageOperationException("Error writing to file: " + path);
        } catch (IllegalValueException | ArrayIndexOutOfBoundsException e) {
            throw new FileCorruptedException("File content not in the expected format: " + path);
        }

    }

    /**
     * Rewrites the storage file with the updated task list.
     *
     * @param list The updated task list to be rewritten into the storage file.
     * @throws StorageOperationException if there are errors reading and/or converting data from the file.
     */
    public void rewrite(List<Task> list) throws StorageOperationException {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageOperationException("Error rewriting file: " + path);
        }

        for (Task task : list) {
            save(task);
        }
    }

}

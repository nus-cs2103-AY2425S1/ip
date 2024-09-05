package mylo.storage;

import mylo.data.InsufficientInfoException;
import mylo.task.Task;
import mylo.task.TaskList;
import mylo.utils.exceptions.IllegalValueException;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


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
    private final Path PATH = Paths.get(STORAGE_FILEPATH);

    /**
     * Saves the specified {@code task} data to the storage file.
     *
     * @param task The task instance containing data to be stored in the storage file.
     * @throws StorageOperationException if there are errors converting and/or storing data to the file.
     */
    public void save(Task task) throws StorageOperationException {
        try (FileWriter fileWriter = new FileWriter(STORAGE_FILEPATH, true)) {
            if (!Files.exists(PATH)) Files.createFile(PATH);
            fileWriter.write(task.storageFormat() + System.lineSeparator());
        } catch (IOException e) {
            throw new StorageOperationException("Error writing to file: " + PATH);
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

        if (!Files.exists(PATH) || !Files.isRegularFile(PATH)) {
            return new TaskList();
        }

        try {
            return TaskList.decodeTxt(Files.readAllLines(PATH));
        } catch (IOException e) {
            throw new StorageOperationException("Error writing to file: " + PATH);
        } catch (IllegalValueException | ArrayIndexOutOfBoundsException e) {
            throw new FileCorruptedException("File content not in the expected format: " + PATH);
        }

    }

    /**
     * Rewrites the storage file with the updated task list.
     *
     * @param list The updated task list to be rewritten into the storage file.
     * @throws StorageOperationException if there are errors reading and/or converting data from the file.
     */
    public void rewrite(ArrayList<Task> list) throws StorageOperationException {
        try {
            Files.delete(PATH);
        } catch (IOException e) {
            throw new StorageOperationException("Error rewriting file: " + PATH);
        }

        for (Task task : list) {
            save(task);
        }
    }

}

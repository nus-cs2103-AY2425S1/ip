package storage;

import data.InsufficientInfoException;
import task.Task;
import task.TaskList;
import utils.exceptions.IllegalValueException;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Storage {
    /**
     * File path to store task list.
     */
    public static final String STORAGE_FILEPATH = "tasks.txt";

    /**
     * Locate to the file that stores task list.
     */
    private final Path PATH = Paths.get(STORAGE_FILEPATH);

    /**
     * Saves the {@code task} data to the storage file.
     *
     * @param task task instance containing data to be stored in the storage file.
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
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
     * Loads the {@code TaskList} data from this storage file, and then returns it.
     * Returns an empty {@code TaskList} if the file does not exist, or is not a regular file.
     *
     * @throws StorageOperationException if there were errors reading and/or converting data from file.
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
     * Rewrite the storage file with updated task list.
     *
     * @param list updated task list to be rewritten into the storage file.
     * @throws StorageOperationException if there were errors reading and/or converting data from file.
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

package momo.command;

import static momo.Momo.FILE_PATH;

import java.io.IOException;

import momo.Storage;
import momo.StorageException;
import momo.task.Task;
import momo.task.TaskList;

/**
 * The {@code AddCommand} class provides implementations for adding tasks
 * to the storage and for printing confirmation messages that display tasks
 * which have been added
 */
public class AddCommand extends Command {

    /**
     * Saves specified task to storage file specified by the FILE_PATH. If a IOException
     * occurs during the process a StorageException is returned
     *
     * @param storage The storage object responsible for saving tasks
     * @param task    The specified task object to save to storage
     * @throws StorageException If an error occurs while writing the task to storage
     */
    public static void addToStorage(Storage storage, Task task) throws StorageException {
        assert task != null : "Task should not be null";

        try {
            storage.addTaskToFile(FILE_PATH, task.toFileString());
        } catch (IOException ioe) {
            throw new StorageException(ioe.getMessage());
        }
    }

    public static String returnCountString(TaskList tasks) {
        return String.format("\nNow you have %d task(s) in the list%n", tasks.getCount());
    }
}

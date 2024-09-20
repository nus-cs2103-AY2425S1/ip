package momo.command;

import static momo.Momo.FILE_PATH;

import java.io.IOException;

import momo.Storage;
import momo.StorageException;
import momo.task.Task;
import momo.task.TaskList;

/**
 * The {@code AddCommand} class provides implementations for adding tasks
 * to the storage and for returning a string showing current count of task list.
 */
public class AddCommand extends Command {

    /**
     * Saves specified task to storage file specified by the FILE_PATH. If a IOException
     * occurs during the process a StorageException is returned.
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

    /**
     * Computes the string showing the number of tasks after the addition of task
     *
     * @param tasks The TaskList containing a list of tasks
     * @return The string containing number of tasks in the list
     */
    public static String returnCountString(TaskList tasks) {
        return String.format("\nNow you have %d task(s) in the list%n", tasks.getCount());
    }
}

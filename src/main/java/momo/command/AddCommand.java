package momo.command;

import momo.Storage;
import momo.StorageException;
import momo.Ui;
import momo.task.Task;

import java.io.IOException;

import static momo.Momo.FILE_PATH;

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

    /**
     * Prints a confirmation message displaying the task which has just
     * been successfully added to the TaskList and storage file
     *
     * @param task Task object to be printed
     */
    public static void printTaskAdded(Task task, Ui ui) {
        ui.printDialogue("Noted. I've added this task:\n " + task);
    }
}

package momo.command;

import momo.MomoException;
import momo.Storage;
import momo.StorageException;
import momo.task.Task;

import java.io.IOException;

import static momo.Momo.FILE_PATH;

public class AddCommand extends Command {

    public static void addToStorage(Storage storage, Task task) throws StorageException {
        try {
            storage.addTaskToFile(FILE_PATH, task.toFileString());
        } catch (IOException ioe) {
            throw new StorageException(ioe.getMessage());
        }
    }

    public static void printTaskAdded(Task task) {
        System.out.println("Noted. I've added this task:\n " + task);
    }
}

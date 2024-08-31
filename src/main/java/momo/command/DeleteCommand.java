package momo.command;

import momo.InvalidCommandException;
import momo.Storage;
import momo.StorageException;
import momo.task.Task;
import momo.task.TaskList;

import java.io.IOException;

import static momo.Momo.FILE_PATH;

/**
 * Handles the delete task functionality, handling the validation of input and
 * updating the {@link TaskList} and rewriting the {@link Storage} file such that
 * they do not include the deleted task anymore
 */
public class DeleteCommand {
    public static void run(String input, TaskList tasks, Storage storage) throws InvalidCommandException, StorageException {
        try {
            int index = Integer.parseInt(input.substring(6).trim()) - 1;

            if (index >= tasks.getCount() || index < 0) {
                throw new InvalidCommandException("You can only delete a number your task list contains");
            }

            Task deletedTask = tasks.getTask(index);

            tasks.deleteTask(index);

            System.out.println("Noted. I've removed this task:\n " + deletedTask);
            System.out.printf("Now you have %d task(s) in the list%n", tasks.getCount());
            storage.RewriteTasksToFile(FILE_PATH, tasks.getTaskList());

        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Watch out: You did not format your number properly...");
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }

    }




}

package momo.command;

import momo.InvalidCommandException;
import momo.Storage;
import momo.StorageException;
import momo.task.Task;
import momo.task.TaskList;

import java.io.IOException;

import static momo.Momo.FILE_PATH;

public class UnmarkCommand {
    public static void run(String input, TaskList tasks, Storage storage) throws InvalidCommandException, StorageException {

        try {
            int index = Integer.parseInt(input.substring(6).trim()) - 1;

            if (index >= tasks.getCount() || index < 0) {
                throw new InvalidCommandException("You can only unmark a number your task list contains");
            }

            Task taskToUnmark = tasks.getTask(index);
            taskToUnmark.unmark();

            System.out.println("Noted. I've unmarked this task:\n " + taskToUnmark);
            storage.RewriteTasksToFile(FILE_PATH, tasks.getTaskList());

        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Watch out: You did format not your number properly...");
        }
        catch (IOException e) {
            throw new StorageException(e.getMessage());
        }

    }

}

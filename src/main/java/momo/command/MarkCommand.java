package momo.command;

import momo.InvalidCommandException;
import momo.Storage;
import momo.StorageException;
import momo.task.Task;
import momo.task.TaskList;

import java.io.IOException;

import static momo.Momo.FILE_PATH;

public class MarkCommand {
    public static void run(String input, TaskList tasks, Storage storage) throws InvalidCommandException, StorageException {

        try {
            int index = Integer.parseInt(input.substring(4).trim()) - 1;

            if (index >= tasks.getCount() || index < 0) {
                throw new InvalidCommandException("You can only mark a number your task list contains");
            }

            Task taskToMark = tasks.getTask(index);
            taskToMark.markComplete();

            System.out.println("Noted. I've marked this task:\n " + taskToMark);
            storage.RewriteTasksToFile(FILE_PATH, tasks.getTaskList());

        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Watch out: You did format not your number properly...");
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }

    }

}

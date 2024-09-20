package momo.command;

import static momo.Momo.FILE_PATH;

import java.io.IOException;

import momo.Storage;
import momo.StorageException;
import momo.exception.InvalidCommandException;
import momo.task.Task;
import momo.task.TaskList;

/**
 * Handles the delete task functionality, handling the validation of input and
 * updating the {@link TaskList} and rewriting the {@link Storage} file such that
 * they do not include the deleted task anymore
 */
public class DeleteCommand {

    public static final int COMMAND_PREFIX_OFFSET = 6;

    /**
     * Runs delete command, validating and deleting user task from list and rewriting file if the delete command is
     * valid, with comprehensive exception handling
     * @param input
     * @param tasks
     * @param storage
     * @throws InvalidCommandException
     * @throws StorageException
     */
    public static String run(String input, TaskList tasks, Storage storage) throws InvalidCommandException,
            StorageException {
        assert tasks != null : "TaskList should not be null";

        try {
            int index = Integer.parseInt(input.substring(COMMAND_PREFIX_OFFSET).trim()) - 1;
            if (index >= tasks.getCount() || index < 0) {
                throw new InvalidCommandException("You can only delete a number your task list contains");
            }

            Task deletedTask = tasks.getTask(index);

            assert (tasks.getCount() > 0);
            tasks.deleteTask(index);

            storage.RewriteTasksToFile(FILE_PATH, tasks.getTaskList());

            return "Noted. I've removed this task:\n" + deletedTask + String.format("\n Now you have %d task(s) in "
                    + "the list%n", tasks.getCount());

        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Watch out: You did not format your number properly...");
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }

    }


}

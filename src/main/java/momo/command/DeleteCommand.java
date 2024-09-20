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
 * they do not include the deleted task anymore.
 */
public class DeleteCommand {

    public static final int COMMAND_PREFIX_OFFSET = 6;

    /**
     * Executes the delete command, validating user input, deleting the specified task from
     * the task list, and updating the storage file accordingly. If the input is invalid
     * or the specified task index is out of bounds, an appropriate exception is thrown.
     * Upon successful deletion, the updated task list is written back to the storage file.
     *
     * @param input  The user input that specifies the task to delete, expected to be a valid index.
     * @param tasks  The current list of tasks from which a task will be deleted.
     * @param storage The storage handler responsible for rewriting the updated task list to the file.
     * @return A string message indicating the task has been successfully deleted.
     * @throws InvalidCommandException If input is wrongly formatted or has invalid number.
     * @throws StorageException If there is an error writing the updated task list to the storage file.
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

            storage.rewriteTasksToFile(FILE_PATH, tasks.getTaskList());

            return "Noted. I've removed this task:\n" + deletedTask + String.format("\n Now you have %d task(s) in "
                    + "the list%n", tasks.getCount());

        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Watch out: You did not format your number properly...");
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }

    }


}

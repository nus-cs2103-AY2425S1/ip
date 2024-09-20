package momo.command;

import static momo.Momo.FILE_PATH;

import java.io.IOException;

import momo.Storage;
import momo.StorageException;
import momo.exception.InvalidCommandException;
import momo.task.Task;
import momo.task.TaskList;

/**
 * Handles the functionality of marking a command, validating the numerical input
 * and updating the task to show that it has been marked completed.
 */
public class MarkCommand {

    public static final int COMMAND_PREFIX_OFFSET = 4;

    /**
     * Runs the {@code MarkCommand}, handling the validation of the input, updating the
     * specified task's completion in the {@link TaskList} and rewriting the {@link Storage}
     * to include the task with the updated completion.
     *
     * @param input User command which begins with 'mark'.
     * @param tasks TaskList object including all tasks the user has added.
     * @param storage Storage object which handles saving new data persistently.
     * @throws InvalidCommandException thrown when number is not in list or is improperly formatted.
     * @throws StorageException thrown when task is not rewritten to file successfully.
     */
    public static String run(String input, TaskList tasks, Storage storage) throws InvalidCommandException,
            StorageException {

        try {
            int index = Integer.parseInt(input.substring(COMMAND_PREFIX_OFFSET).trim()) - 1;

            if (index >= tasks.getCount() || index < 0) {
                throw new InvalidCommandException("You can only mark a number your task list contains, mortal.");
            }

            Task taskToMark = tasks.getTask(index);
            taskToMark.markComplete();

            storage.rewriteTasksToFile(FILE_PATH, tasks.getTaskList());
            return "Good human. I've marked this task:\n " + taskToMark;

        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Watch out: You did format not your number properly... don't make me "
                    + "warn you again (ʘ  ʘ)");
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }

    }

}

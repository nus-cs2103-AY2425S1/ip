package momo.command;

import static momo.Momo.FILE_PATH;

import java.io.IOException;

import momo.Storage;
import momo.StorageException;
import momo.Ui;
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
     * @param ui
     * @throws InvalidCommandException
     * @throws StorageException
     */
    public static void run(String input, TaskList tasks, Storage storage, Ui ui) throws InvalidCommandException,
            StorageException {

        try {
            int index = Integer.parseInt(input.substring(COMMAND_PREFIX_OFFSET).trim()) - 1;
            if (index >= tasks.getCount() || index < 0) {
                throw new InvalidCommandException("You can only delete a number your task list contains");
            }

            Task deletedTask = tasks.getTask(index);

            tasks.deleteTask(index);

            ui.printDialogue("Noted. I've removed this task:\n " + deletedTask);
            ui.printDialogue(String.format("Now you have %d task(s) in the list%n", tasks.getCount()));
            storage.RewriteTasksToFile(FILE_PATH, tasks.getTaskList());

        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Watch out: You did not format your number properly...");
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }

    }


}

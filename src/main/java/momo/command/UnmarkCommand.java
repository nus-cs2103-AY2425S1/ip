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
 * Handles the functionality of unmarking a command, validating the numerical input
 * and updating the task to show that it has been marked incomplete.
 */
public class UnmarkCommand {

    /**
     * Runs the {@code MarkCommand}, handling the validation of the input, updating the
     * specified task's completion in the {@link TaskList} and rewriting the {@link Storage}
     * to include the task with the updated completion.
     *
     * @param input   User command which begins with 'mark'
     * @param tasks   TaskList object including all tasks the user has added
     * @param storage Storage object which handles saving new data persistently
     * @throws InvalidCommandException thrown when number is not in list or is improperly formatted
     * @throws StorageException        thrown when task is not rewritten to file successfully
     */
    public static void run(String input, TaskList tasks, Storage storage, Ui ui) throws InvalidCommandException,
            StorageException {

        try {
            int index = Integer.parseInt(input.substring(6).trim()) - 1;

            if (index >= tasks.getCount() || index < 0) {
                throw new InvalidCommandException("You can only unmark a number your task list contains");
            }

            Task taskToUnmark = tasks.getTask(index);
            taskToUnmark.unmark();

            ui.printDialogue("Noted... I've unmarked this task:\n " + taskToUnmark);
            storage.RewriteTasksToFile(FILE_PATH, tasks.getTaskList());

        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Watch out: You did format not your number properly...");
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }

    }

}

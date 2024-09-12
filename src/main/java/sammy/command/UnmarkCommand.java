package sammy.command;

import sammy.task.TaskList;
import sammy.Ui;
import sammy.SammyException;
import sammy.Storage;
import sammy.task.Task;
import sammy.exceptions.InvalidTaskNumberException;

import java.io.IOException;

/**
 * Represents a command to unmark a task as not done in the task list.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to be unmarked as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command by marking the task at the specified index as not done,
     * displaying the confirmation to the user, and saving the updated task list to storage.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui The UI object to interact with the user.
     * @param storage The storage object to save the task list.
     * @throws SammyException If the task index is invalid.
     * @throws IOException If an I/O error occurs while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws SammyException, IOException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }
        Task task = tasks.get(index);
        task.markAsNotDone();
        return ui.showUnmarkTask(task);
    }
}



package sammy.command;

import sammy.task.TaskList;
import sammy.Ui;
import sammy.SammyException;
import sammy.Storage;
import java.io.IOException;
import sammy.task.Task;
import sammy.exceptions.InvalidTaskNumberException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        assert index >= 0 : "Index cannot be negative";
        this.index = index;
    }

    /**
     * Executes the delete command by removing the task at the specified index from the TaskList,
     * displaying a confirmation to the user, and saving the updated task list to storage.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui The UI object to interact with the user.
     * @param storage The storage object to save the task list.
     * @throws SammyException If the task number is invalid.
     * @throws IOException If an I/O error occurs while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws SammyException, IOException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }
        Task removedTask = tasks.remove(index);
        storage.save(tasks);
        return ui.showRemoveTask(removedTask, tasks.size());

    }
}


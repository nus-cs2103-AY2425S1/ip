package command;

import java.io.IOException;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete a task from the task list.
     *
     * @param tasks   The task list to delete the task from.
     * @param ui      The user interface to display the result of the command.
     * @param storage The storage to save the task list to.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.remove(index - 1);
            storage.save(tasks);
            return ui.showTaskDeleted(task, tasks.size());
        } catch (IndexOutOfBoundsException | IOException e) {
            return ui.showSavingError();
        }
    }
}

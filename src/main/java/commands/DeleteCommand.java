package commands;

import tasks.Task;
import tasks.TaskList;
import utils.Storage;
import utils.Ui;

/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
    private final int index;

    /**
     * Constructs a new DeleteCommand.
     *
     * @param index Index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete a task from the task list.
     *
     * @param tasks   TaskList from which the task is deleted.
     * @param ui      UI to handle user interaction.
     * @param storage Storage to save the task list.
     * @return Result message of task deletion.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.remove(index);
        storage.save(tasks);
        return ui.showTaskDeleted(task, tasks.size());
    }
}

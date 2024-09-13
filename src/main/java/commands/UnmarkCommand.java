package commands;

import tasks.Task;
import tasks.TaskList;
import utils.Storage;
import utils.Ui;


/**
 * Command to unmark a task as not done in the task list.
 */
public class UnmarkCommand implements Command {
    private final int index;

    /**
     * Constructs a new UnmarkCommand.
     *
     * @param index Index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to unmark a task as not done in the task list.
     *
     * @param tasks   TaskList containing the task to be unmarked.
     * @param ui      UI to handle user interaction.
     * @param storage Storage to save the task list.
     * @return Result message showing the unmarked task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(index);
        task.unMark();
        storage.save(tasks);
        return ui.showTaskUnmarked(task);
    }
}

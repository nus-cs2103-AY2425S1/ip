package commands;

import tasks.Task;
import tasks.TaskList;
import utils.Storage;
import utils.Ui;


/**
 * Command to mark a task as done in the task list.
 */
public class MarkCommand implements Command {
    private final int index;

    /**
     * Constructs a new MarkCommand.
     *
     * @param index Index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark a task as done in the task list.
     *
     * @param tasks   TaskList containing the task to be marked.
     * @param ui      UI to handle user interaction.
     * @param storage Storage to save the task list.
     * @return Result message showing the marked task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(index);
        task.mark();
        storage.save(tasks);
        return ui.showTaskMarked(task);
    }
}

package strand.command;

import strand.Storage;
import strand.TaskList;
import strand.Ui;
import strand.exception.StrandException;
import strand.task.Task;

/**
 * The {@code MarkCommand} class represents a command to mark a task as done or not done.
 */
public class MarkCommand extends Command {
    private final Integer index;
    private final Boolean mark;

    /**
     * Constructs a new {@code MarkCommand} with the specified task index and mark status.
     *
     * @param index The index of the task to be marked.
     * @param mark  {@code true} to mark the task as done, {@code false} to mark it as not done.
     */
    public MarkCommand(Integer index, Boolean mark) {
        this.index = index;
        this.mark = mark;
    }

    /**
     * Executes the mark command by updating the status of the task in the task list.
     *
     * @param tasks   The current list of tasks to which the new task will be added.
     * @param ui      The {@code Ui} object used to interact with the user and show task updates.
     * @param storage The {@code Storage} object used to save the updated list of tasks.
     * @throws StrandException If there is an error during task addition, UI update, or storage operation.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws StrandException {
        Task task = tasks.mark(this.index, this.mark);
        String output = ui.taskMarked(task, this.mark);
        storage.save(tasks.convertToFileFormat());
        return output;
    }
}

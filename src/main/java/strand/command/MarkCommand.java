package strand.command;

import strand.Storage;
import strand.TaskList;
import strand.Ui;
import strand.exception.StrandException;
import strand.exception.StrandWrongCommandException;
import strand.task.Task;

/**
 * The {@code MarkCommand} class represents a command to mark status and priority of a task.
 */
public class MarkCommand extends Command {
    private final Integer index;
    private Boolean markTask = false;
    private Task.PriorityEnum priority = null;

    /**
     * Constructs a new {@code MarkCommand} with the specified task index and mark status.
     *
     * @param index The index of the task to be marked.
     * @param markTask  {@code true} to mark the task as done, {@code false} to mark it as not done.
     */
    public MarkCommand(Integer index, Boolean markTask) {
        this.index = index;
        this.markTask = markTask;
    }

    /**
     * Constructs a new {@code MarkCommand} with the specified task index, mark status and priority.
     *
     * @param index    The index of the task to be marked.
     * @param priority The priority that the task will be assigned.
     */
    public MarkCommand(Integer index, String priority) throws StrandException {
        this.index = index;
        assert priority != null : "Priority cannot be null";
        try {
            this.priority = Task.PriorityEnum.valueOf(priority.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new StrandWrongCommandException();
        }
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
        Task task;
        String output;
        if (this.priority != null) {
            task = tasks.mark(this.index, this.priority);
            output = ui.priorityAssigned(task, this.priority);
        } else {
            task = tasks.mark(this.index, this.markTask);
            output = ui.taskMarked(task, this.markTask);
        }
        storage.save(tasks.convertToFileFormat());
        return output;
    }
}

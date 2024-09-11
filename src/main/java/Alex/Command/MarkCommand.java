package Alex.Command;

import Alex.Exceptions.AlexException;
import Alex.Storage.Storage;
import Alex.Task.Task;
import Alex.Task.TaskList;
import Alex.Ui.Ui;

/**
 * Command to mark a task as done or not done by index and save the updated list to storage.
 */
public class MarkCommand extends CommandBase {
    private int index;
    private boolean markAsDone;

    /**
     * Constructs a MarkCommand with the specified task index and status.
     * @param index The index of the task to be marked.
     * @param markAsDone True to mark the task as done, false to mark as not done.
     */
    public MarkCommand(int index, boolean markAsDone) {
        this.index = index;
        this.markAsDone = markAsDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlexException {
        try {
            Task task = tasks.getTask(index);
            if (markAsDone) {
                task.markAsDone();
                ui.showTaskStatusChange("Nice! I've marked this task as done:", index);
            } else {
                task.markAsNotDone();
                ui.showTaskStatusChange("OK, I've marked this task as not done yet:", index);
            }
            storage.save(tasks.getAllTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new AlexException("Task not found.");
        }
    }
}

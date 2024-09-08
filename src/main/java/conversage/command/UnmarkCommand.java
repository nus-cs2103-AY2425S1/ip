package conversage.command;

import conversage.exception.ConverSageException;
import conversage.storage.Storage;
import conversage.task.Task;
import conversage.task.TaskList;
import conversage.ui.Ui;
import javafx.beans.binding.StringBinding;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    // Note that when user does "mark 1" => the 1 is a string
    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param taskIndex the index of the task to be unmarked as not done.
     */
    public UnmarkCommand(String taskIndex) {
        this.taskIndex = Integer.parseInt(taskIndex);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ConverSageException {
        Task task = tasks.getTask(taskIndex - 1); // since tasks are 0-indexed
        task.markAsUndone();
        ui.showMessage("I've marked this task as not done yet, get to it quickly.\n" + task.toString());
        ui.showLine();

        // Update storage
        storage.save(tasks.getTasks());
        return "I've marked this task as not done yet, get to it quickly.\n" + task.toString();
    }
}
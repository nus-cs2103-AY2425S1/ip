package shrimp.command;

import shrimp.task.Task;
import shrimp.task.TaskList;
import shrimp.utility.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
    /** The index of the task to be deleted.*/
    private final int index;

    /**
     * Constructs a {@code DeleteCommand} with the specified task index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        assert index >= 0 : "index should be more than 0";
        this.index = index;
    }

    /**
     * Executes the delete command by removing the task from the task list
     * and printing the result to the user interface.
     *
     * @param tasks The list of tasks from which the task will be deleted.
     * @param ui    The user interface to print the result of the command.
     */
    @Override
    public String run(TaskList tasks, Ui ui) {
        Task task = tasks.getTask(index);
        tasks.deleteTask(index);
        return ui.printDelete(task, tasks);
    }
}

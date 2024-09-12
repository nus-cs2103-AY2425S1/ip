package shrimp.command;

import shrimp.task.Task;
import shrimp.task.TaskList;
import shrimp.utility.Ui;

/**
 * Represents a command to add a new task to the task list.
 */
public class AddCommand implements Command {

    /** Task to be added to the task list. */
    private final Task task;

    /**
     * Constructs an {@code AddCommand} with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        assert task != null : "Task is null";
        this.task = task;
    }

    /**
     * Executes the add command by adding the task to the task list and
     * printing the result to the user interface.
     *
     * @param tasks The list of tasks to which the task will be added.
     * @param ui    The user interface to print the result of the command.
     */
    @Override
    public String run(TaskList tasks, Ui ui) {
        tasks.addTask(task);
        return ui.printAdd(task, tasks);
    }
}

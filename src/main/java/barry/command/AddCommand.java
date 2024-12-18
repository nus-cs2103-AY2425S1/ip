package barry.command;

import barry.task.Task;
import barry.util.TaskList;
import barry.util.Ui;
import barry.util.Storage;

import java.io.IOException;

/**
 * Represents an abstract command that adds a task to the task list.
 */
public abstract class AddCommand extends Command {
    protected Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added by this command.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command.
     * Adds the task to the task list, displays addition to the user,
     * and saves the updated task to storage.
     *
     * @param allTasks The list of all tasks on which the task would be added to.
     * @param ui The user interface for interaction with the user.
     * @param storage The storage system for saving updated tasks.
     * @throws IOException If there is an error during input or output operations.
     */
    @Override
    public void execute(TaskList allTasks, Ui ui, Storage storage) throws IOException {
        allTasks.addTask(task);
        ui.showAddTask(task, allTasks.size());
        storage.save(allTasks.getAllTasks());
    }
}

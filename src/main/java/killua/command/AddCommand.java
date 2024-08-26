package killua.command;

import killua.task.Task;
import killua.util.KilluaException;
import killua.util.Storage;
import killua.util.TaskList;
import killua.util.Ui;

import java.io.IOException;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Creates an AddCommand with the specified task to be added.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add the task to the task list.
     * Updates the user interface to show the task has been added and saves the updated task list to storage.
     *
     * @param tasks The task list to which the task will be added.
     * @param ui The user interface to interact with.
     * @param storage The storage to save the updated task list.
     * @throws KilluaException If there is an error related to task handling.
     * @throws IOException If there is an error in reading or writing to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KilluaException, IOException {
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.getTasks().size());
        storage.save(tasks);
    }
}

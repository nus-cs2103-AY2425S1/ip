package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand implements Command {
    private Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command, adding the task to the task list, displaying a message,
     * and saving the updated task list to storage.
     *
     * @param tasks The task list where the task will be added.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save the updated task list.
     * @throws IOException If an I/O error occurs while saving the tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.getSize());
        storage.save(tasks.getTasks());
    }

    /**
     * Returns whether this command will terminate the application.
     *
     * @return false, as the add command does not terminate the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
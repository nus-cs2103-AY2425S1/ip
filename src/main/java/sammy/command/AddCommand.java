package sammy.command;

import sammy.Storage;
import sammy.task.TaskList;
import sammy.Ui;
import sammy.task.Task;

import java.io.IOException;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        assert task != null : "Task cannot be null";
        this.task = task;
    }

    /**
     * Executes the add command by adding the task to the TaskList, showing a confirmation to the user,
     * and saving the updated task list to storage.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui The UI object to interact with the user.
     * @param storage The storage object to save the task list.
     * @throws IOException If an I/O error occurs while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "UI cannot be null";
        assert storage != null : "Storage cannot be null";
        tasks.add(task);
        storage.save(tasks);

        return ui.showAddTask(task, tasks.size());
    }
}



package Gary.command;

import java.io.IOException;
import Gary.TaskList;
import Gary.Ui;
import Gary.Storage;
import Gary.task.Task;

/**
 * Represents an AddCommand that adds a task to the TaskList.
 */
public class AddCommand extends Command {
    protected Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the AddCommand by adding the task to the task list, displaying the task,
     * and saving the updated task list to storage.
     *
     * @param taskLists The task list where the task will be added.
     * @param ui The UI object to display the added task.
     * @param storage The storage object to save the updated task list.
     */
    @Override
    public void execute(TaskList taskLists, Ui ui, Storage storage) {
        taskLists.addTask(task);
        ui.addTask(task, taskLists.size());
        try {
            storage.saveTask(taskLists);
        } catch (IOException e) {
            // Handle the IOException, such as by logging or showing an error message
        }
    }

    /**
     * Indicates whether this command causes the application to terminate.
     *
     * @return false because AddCommand does not terminate the application.
     */
    @Override
    public boolean isBye() {
        return false;
    }

    /**
     * Checks if two AddCommand objects are equal by comparing their tasks.
     *
     * @param o The object to compare with.
     * @return true if the given object is an AddCommand with the same task, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof AddCommand) {
            return ((AddCommand) o).task.equals(this.task);
        }
        return false;
    }
}

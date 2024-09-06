package samson.command;

import java.io.IOException;

import samson.Storage;
import samson.Ui;
import samson.task.Task;
import samson.task.TaskList;


/**
 * The <code> AddCommand </code> class represents a command that adds a new task to the task list.
 * This command handles the addition of <code> ToDo </code>, <code> Deadline </code>, <code> Event </code>.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an <code> AddCommand </code> with the specified task to be added.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add the task to the task list, displays a confirmation message,
     * and saves the updated task list to the storage file.
     *
     * @param taskList The list of tasks to which the new task will be added.
     * @param ui The UI object used to display messages to the user.
     * @param storage The storage object used to save tasks to the file.
     * @throws IOException If an I/O error occurs while saving the tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.addTask(task);
        storage.saveTasksToFile(taskList.getTasks());
        return ui.showTaskAdded(task, taskList);
    }

    /**
     * Indicates whether this command should cause the application to exit.
     *
     * @return false because the AddCommand does not trigger an exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

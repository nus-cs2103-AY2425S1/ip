package taskon.commands;

import taskon.storage.Storage;
import taskon.task.Task;
import taskon.task.TaskList;
import taskon.task.Todo;
import taskon.ui.Ui;

/**
 * Represents a command to add a todo task to the task list.
 * <p>
 * This class handles the creation and addition of a todo task with a specified
 * description. It updates the user interface to show the added task and saves
 * the updated task list to storage.
 * </p>
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private Task task;

    /**
     * Constructs a {@code TodoCommand} with the specified task description.
     *
     * @param description The description of the todo task.
     */
    public TodoCommand(String description) {
        this.task = new Todo(description);
    }

    /**
     * Returns the todo task associated with this command.
     *
     * @return The todo task.
     */
    public Task getTask() {
        return this.task;
    }

    /**
     * Executes the command to add a todo task to the task list.
     * <p>
     * This method adds the todo task to the task list, updates the user interface
     * to show the added task, and saves the updated task list to storage.
     * </p>
     *
     * @param taskList The list of tasks where the todo will be added.
     * @param ui       The user interface that handles the display of messages to the user.
     * @param storage  The storage that handles saving the updated task list.
     * @return A string message that confirms the addition of the task to the task list, including
     *     the total number of tasks in the list after the addition.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        storage.saveTasks(taskList);
        return ui.showTaskAdded(task, taskList.size());
    }
}

package justbot.command;

import justbot.storage.Storage;
import justbot.task.Task;
import justbot.task.TaskList;
import justbot.task.Todo;
import justbot.ui.Ui;

/**
 * Represents a command to add a Todo task to the task list in the Justbot application.
 * The TodoCommand creates a new Todo task with a specified description.
 */
public class TodoCommand extends Command {
    private Todo todoTask;

    /**
     * Constructs a TodoCommand with the specified task description.
     *
     * @param description The description of the Todo task.
     */
    public TodoCommand(String description) {
        this.todoTask = new Todo(description.trim());
    }

    /**
     * Executes the command to add the Todo task to the task list.
     * The task is added to the task list, a confirmation message is displayed, and the task list is saved to storage.
     *
     * @param taskList The list of tasks to which the Todo task is added.
     * @param ui The UI instance used to display messages to the user.
     * @param storage The Storage instance used to save the updated task list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this.todoTask);
        storage.saveTasks(taskList);
        return ui.addTaskMessage(taskList, todoTask);
    }

    /**
     * Returns the Todo task associated with this command.
     *
     * @return The Todo task created by this command.
     */
    @Override
    public Task getTask() {
        return this.todoTask;
    }
}

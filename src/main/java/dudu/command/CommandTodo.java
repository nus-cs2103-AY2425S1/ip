package dudu.command;

import java.io.IOException;

import dudu.task.Task;
import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

/**
 * Represents a to-do task creation user command into the chatbot
 */
public class CommandTodo extends Command {

    private Task task;

    /**
     * Constructs a CommandTodo with the specified task.
     *
     * @param task The to-do task to be added.
     */
    public CommandTodo(Task task) {
        this.task = task;
    }

    /**
     * Executes the command by adding the to-do task to the task list, updating
     * the user interface with the task details, and saving the updated task list to storage.
     *
     * @param taskList The task list on which the command is executed.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save the task.
     * @throws IOException If there is an error during saving the task to storage.
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        int size = taskList.addTask(task);
        assert size > 0 : "Task not added to task list";
        storage.rewriteFile(taskList);
        return ui.addTask(task, size);
    }
}

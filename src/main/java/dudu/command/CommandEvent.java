package dudu.command;

import java.io.IOException;

import dudu.task.Task;
import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

/**
 * Represents a event task creation user command into the chatbot
 */
public class CommandEvent extends Command {
    private Task task;

    /**
     * Constructs a CommandEvent with the specified task.
     *
     * @param task The event task to be added.
     */
    public CommandEvent(Task task) {
        this.task = task;
    }

    /**
     * Executes the command by adding the event task to the task list, updating
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

    /**
     * Indicates that this command will not cause the application to exit.
     *
     * @return false, as this command always does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

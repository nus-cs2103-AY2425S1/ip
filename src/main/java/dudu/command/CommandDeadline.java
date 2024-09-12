package dudu.command;

import java.io.IOException;

import dudu.task.Task;
import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

/**
 * Represents a deadline task creation user command into the chatbot
 */
public class CommandDeadline extends Command {
    private Task task;

    /**
     * Constructs a CommandDeadline with the specified task.
     *
     * @param task The deadline task to be added.
     */
    public CommandDeadline(Task task) {
        this.task = task;
    }

    /**
     * Executes the command by adding the deadline task to the task list, updating
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

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CommandDeadline)) {
            return false;
        }
        CommandDeadline otherTask = (CommandDeadline) o;
        return this.task.equals(otherTask.task);
    }
}

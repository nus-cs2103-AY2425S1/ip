package friday.command;

import java.io.IOException;

import friday.task.Task;
import friday.task.TaskList;
import friday.task.Todo;
import friday.util.FridayException;
import friday.util.Storage;
import friday.util.Ui;

/**
 * Represents a command to add a to-do task to the task list.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructs a TodoCommand with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the to-do command, adding the to-do task to the task list and saving it.
     *
     * @param tasks   The task list to be modified by the command.
     * @param ui      The user interface for interacting with the user.
     * @param storage The storage for saving the task list.
     * @return The string representation of adding a new task to the task list.
     * @throws IOException       If an input/output error occurs during execution.
     * @throws FridayException   If there is an error specific to the command execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, FridayException {
        Task task = new Todo(description);
        tasks.addTask(task);
        storage.saveTasks(tasks.getTasks());
        return ui.showTaskAdded(task, tasks.getSize());
    }
}

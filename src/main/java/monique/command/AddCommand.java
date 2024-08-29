package monique.command;

import monique.storage.Storage;
import monique.task.Task;
import monique.tasklist.TaskList;
import monique.ui.Ui;

/**
 * Represents a command to add a task to the task list.
 * This command adds a specified <code>Task</code> to the <code>TaskList</code> and informs the
 * <code>Ui</code> about the addition.
 */
public class AddCommand extends Command {

    private final Task task;

    /**
     * Constructs an <code>AddCommand</code> with the specified task.
     *
     * @param task the task to be added to the task list
     */
    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     * Executes the add command by adding the task to the task list and updating the UI.
     *
     * @param tasks the <code>TaskList</code> where the task will be added
     * @param ui the <code>Ui</code> instance used to display messages
     * @param storage the <code>Storage</code> instance used to manage data persistence
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.task);
        ui.addMessage(this.task);
    }

    /**
     * Returns whether this chatbot will be active after the command executes.
     * @return true, indicating that the command is active
     */
    @Override
    public boolean isActive() {
        return true;
    }

    /**
     * Returns the task associated with this command.
     *
     * @return the task to be added
     */
    public Task getTask() {
        return this.task;
    }
}

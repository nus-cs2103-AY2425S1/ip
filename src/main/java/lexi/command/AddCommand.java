package lexi.command;

import lexi.exception.LexiException;
import lexi.storage.Storage;
import lexi.task.Task;
import lexi.task.TaskList;
import lexi.ui.Ui;

/**
 * Represents a command to add a task to the task list.
 * When executed, this command adds the specified task, updates the storage, and displays a confirmation message to the user.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command, adding the specified task to the task list.
     * The task list and storage are updated, and a confirmation message is displayed to the user.
     *
     * @param tasks   The list of tasks.
     * @param ui      The UI object to interact with the user.
     * @param storage The storage to update the task list.
     * @throws LexiException If an error occurs while updating the storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LexiException {
        tasks.addTask(task);
        storage.updateStorage(tasks.getTasks());
        int taskSize = tasks.getSize();
        ui.showAddMessage(task, taskSize);
    }

    /**
     * Returns the name of the command.
     *
     * @return The string "ADD".
     */
    @Override
    public String getCommandName() {
        return "ADD";
    }
}

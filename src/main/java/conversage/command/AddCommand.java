package conversage.command;

import conversage.exception.ConverSageException;
import conversage.storage.Storage;
import conversage.task.Task;
import conversage.task.TaskList;
import conversage.ui.Ui;

/**
 * Represents a command to add a task.
 */
public class AddCommand extends Command {

    // conversage.command.AddCommand will have a task to add
    private Task toAdd;

    /**
     * Constructs an AddCommand with the specified task to add.
     *
     * @param toAdd the task to be added.
     */
    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    /**
     * Executes the add command, adding a task to the task list, updating the UI, and saving the task list.
     *
     * @param tasks   The task list to add the task to.
     * @param ui      The UI to update.
     * @param storage The storage to save the task list to.
     * @return A message indicating the task has been added.
     * @throws ConverSageException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ConverSageException {
        tasks.add(toAdd);
        ui.showLine();
        ui.showMessage("Understood, I've added this task: " + toAdd.toString()
                + "\nYou have " + tasks.size() + " tasks in your list.");
        ui.showLine();
        storage.save(tasks.getTasks());

        return "Understood, I've added this task: " + toAdd.toString()
                + "\nYou have " + tasks.size() + " tasks in your list.";
    }

}

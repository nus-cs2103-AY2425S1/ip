package nayana.command;

import nayana.NayanaException;
import nayana.Storage;
import nayana.TaskList;
import nayana.Ui;
import nayana.task.Task;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructs a delete command with the given task index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the delete command by removing the task at the specified index
     * from the task list, updating the storage, and printing the task deletion result.
     *
     * @param tasks   The list of tasks to delete from.
     * @param ui      The user interface to print the result.
     * @param storage The storage to update with the current list of tasks.
     * @throws NayanaException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NayanaException {
        Task task = tasks.delete(index); // Adds the task to the task list.
        storage.writeToFile(tasks.getTasks()); // Updates storage with the new list of tasks.
        ui.printDeleteTask(task, tasks.getSize()); // Displays a confirmation message with the current task list size.
    }

    /**
     * Returns whether this command is an exit command.
     *
     * @return False, as this command does not terminate the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}


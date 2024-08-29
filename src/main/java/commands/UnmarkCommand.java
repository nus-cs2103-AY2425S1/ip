package commands;

import exception.PrimoException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command to mark a task as not done (undone) in the task list.
 * This command marks a specified task at a given index as not done,
 * prints confirmation messages, and updates the storage.
 */
public class UnmarkCommand extends Command {

    private int index;

    /**
     * Constructs an UnmarkCommand with the specified index of the task to be marked as not done.
     *
     * @param index The index of the task in the task list to be marked as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Checks whether this command is an exit command.
     * This command is not an exit command, so it returns false.
     *
     * @return false, as this command does not signify that the application should terminate.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the unmark command by marking the task at the specified index as not done,
     * printing the details of the updated task, and updating the storage.
     *
     * @param tasks The current list of tasks. This parameter allows the command
     *              to modify the task list by marking the task at the specified index as not done.
     * @param ui The user interface component. This parameter is not used in this method.
     * @param storage The storage component. This parameter is used to update the storage
     *                with the modified task list.
     * @throws PrimoException If the index is out of the valid range of the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PrimoException {
        ArrayList<Task> list = tasks.getTasks();
        if (index >= list.size() || index < 0) {
            throw new PrimoException("Please select within the indexes of the task list!");
        }
        list.get(index).markAsUndone();
        System.out.println("\nEl Primo:");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list.get(index));
        storage.updateStorage(tasks);
    }
}

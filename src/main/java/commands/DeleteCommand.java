package commands;

import exception.PrimoException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command to delete a task from the task list.
 * This command removes a task at the specified index from the list,
 * prints a confirmation message, and updates the storage.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructs a DeleteCommand with the specified index of the task to be deleted.
     *
     * @param index The index of the task in the task list to be deleted.
     */
    public DeleteCommand(int index) {
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
     * Executes the delete command by removing the task at the specified index from the task list,
     * printing the details of the removed task, and updating the storage.
     *
     * @param tasks The current list of tasks. This parameter allows the command
     *              to modify the task list by removing the task at the specified index.
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
        System.out.println("\nEl Primo:");
        System.out.println("Noted. I've removed this task:");
        System.out.println(list.get(index));
        list.remove(index);
        storage.updateStorage(tasks);
    }
}

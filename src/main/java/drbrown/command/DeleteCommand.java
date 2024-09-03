package drbrown.command;

import drbrown.task.Task;
import drbrown.utils.DrBrownException;
import drbrown.utils.Storage;
import drbrown.utils.TaskList;
import drbrown.utils.Ui;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    private int itemIndex;

    /**
     * Constructs a DeleteCommand with the specified index of the task to be deleted.
     *
     * @param itemIndex The index of the task to delete from the task list.
     */
    public DeleteCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    /**
     * Executes the delete command by removing the task at the specified index from the task list.
     * It displays the deleted task, updates the task count, and handles exceptions if the index is out of bounds.
     *
     * @param tasks   The TaskList from which the task will be removed.
     * @param ui      The UI object to display messages to the user.
     * @param storage The Storage object for saving changes to the file.
     * @throws DrBrownException If the task index is invalid (out of bounds).
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DrBrownException {
        try {
            ArrayList<Task> list = tasks.getList();
            Task deleteTask = list.get(this.itemIndex);
            tasks.removeItem(this.itemIndex);
            ui.showDeleteTask(deleteTask);
            ui.showEnd();
            ui.showCount(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DrBrownException("You got the count wrong! That’s not how you calculate time travel – you're off by a few gigawatts!");
        }
    }

    /**
     * Indicates whether this command exits the program.
     *
     * @return false, as this command does not exit the program.
     */
    public boolean isExit() {
        return false;
    }

}

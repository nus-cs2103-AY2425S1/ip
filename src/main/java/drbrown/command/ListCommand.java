package drbrown.command;

import drbrown.task.Task;
import drbrown.utils.DrBrownException;
import drbrown.utils.Storage;
import drbrown.utils.TaskList;
import drbrown.utils.Ui;

import java.util.ArrayList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks in the task list.
     * If the task list is empty, it throws a DrBrownException.
     *
     * @param tasks   The TaskList containing the current tasks.
     * @param ui      The UI object to display messages to the user.
     * @param storage The Storage object for saving and loading tasks (not used in this command).
     * @throws DrBrownException If the task list is empty.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DrBrownException {
        if (tasks.getCount() == 0) {
            throw new DrBrownException("Wait a minute, Doc! There's nothing here! We can't go anywhere until you add something to the list!");
        }
        ArrayList<Task> list = tasks.getList();
        ui.showList();
        int listCount = 1;
        for (Task item : list) {
            System.out.println(listCount + ". " + item.toString());
            listCount++;
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

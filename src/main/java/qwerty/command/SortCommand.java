package qwerty.command;

import java.util.HashMap;

import qwerty.Storage;
import qwerty.TaskList;
import qwerty.task.Task;
import qwerty.ui.Ui;

/**
 * This class encapsulates a 'sort' command.
 */
public class SortCommand extends Command {

    public SortCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    /**
     * Sorts the task list and prints the result.
     *
     * @param tasks The TaskList component handling storage of Task objects.
     * @param ui The Ui component handling user interaction.
     * @param storage The Storage component handling read/write to hard disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.sort();
        ui.showQwertyMessage("\nSorted your tasks in O(n!) time. You're welcome."
                + "\nYour list now looks like this:" + tasks.listTasks());
    }
}

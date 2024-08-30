package bimo.command;

import bimo.Storage;
import bimo.TaskList;
import bimo.Ui;

/**
 * Creaetes a command to unmark tasks.
 */
public class UnmarkCommand extends Command {
    private int index;
    /**
     * Instantiates object to set task as uncompleted.
     *
     * @param index Index of task inside list.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Sets status of task as uncompleted.
     *
     * @param tasks List of user tasks.
     * @param ui User interface that interacts with users.
     * @param storage Storage that writes and load files.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (index >= tasks.getLength() || index < 0) {
            ui.showTaskNotFoundError();
            return;
        }
        tasks.getTask(index).markUncompleted();
        storage.overwriteFile(tasks);
        System.out.println("    Good job! I've marked this task as done:");
        System.out.println("       " + tasks.getTask(index).toString());
    }
}

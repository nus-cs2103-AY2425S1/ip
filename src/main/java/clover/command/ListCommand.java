package clover.command;

import clover.util.TaskList;
import clover.util.Ui;
import clover.util.Storage;

import java.io.IOException;

/**
 * Represents a command to show user all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     * Displays all tasks in the task list to the user.
     *
     *
     * @param allTasks The list of all tasks.
     * @param ui The user interface for interaction with the user.
     * @param storage The storage system.
     */
    @Override
    public void execute(TaskList allTasks, Ui ui, Storage storage) {
        ui.showList(allTasks);
    }
}

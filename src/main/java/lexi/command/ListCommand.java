package lexi.command;

import lexi.storage.Storage;
import lexi.task.TaskList;
import lexi.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 * When executed, this command displays all tasks to the user.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, displaying all tasks in the task list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The UI object to interact with the user.
     * @param storage The storage (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showListOfTasks(tasks.getTasks());
    }

    /**
     * Returns the name of the command.
     *
     * @return The string "LIST".
     */
    @Override
    public String getCommandName() {
        return "LIST";
    }
}

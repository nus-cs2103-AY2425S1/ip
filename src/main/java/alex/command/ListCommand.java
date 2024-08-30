package alex.command;

import alex.TaskList;
import alex.Ui;
import alex.Storage;

/**
 * Represents the command by user to list out all the tasks present in the Tasklist.
 */
public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     *
     * Displays all the tasks present in the Tasklist.
     *
     * @param tasks Tasklist that holds the list of Tasks.
     * @param ui Ui object that displays messages to user based on action taken by chatbot.
     * @param storage Storage object that saves changes to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks);
    }

    /**
     * {@inheritDoc}
     *
     * @return false as user is not done yet.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

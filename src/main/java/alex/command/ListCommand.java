package alex.command;

import alex.Storage;
import alex.Ui;
import alex.task.TaskList;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTasks(tasks, "Here are the tasks in your list: ");
    }

    @Override
    public String getCommandType() {
        return "";
    }
}

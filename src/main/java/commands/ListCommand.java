package commands;

import applemazer.Storage;
import applemazer.TaskList;
import applemazer.Ui;

/**
 * Class that represents the "list" command.
 */
public class ListCommand extends Command {
    /**
     * Executes the "list" command by listing down all current tasks in the task list.
     *
     * @param tasks The task list to use.
     * @param storage The storage object containing the filepath which the chatbot saves to and loads from.
     * @param ui The Ui object used to generate the string to print.
     * @return The string to print.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return ui.getFullTaskListString(tasks);
    }

    /**
     * Returns {@code true} as the chatbot should continue running after executing the "list" command.
     * @return {@code true}
     */
    @Override
    public boolean continueProcessing() {
        return true;
    }
}

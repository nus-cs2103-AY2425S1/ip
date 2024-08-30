package alex.command;

import alex.TaskList;
import alex.Ui;
import alex.Storage;

/**
 * Represents the command by user to exit the chatbot.
 */
public class ExitCommand extends Command {

    /**
     * {@inheritDoc}
     *
     * Displays the goodbye message to user.
     *
     * @param tasks Tasklist that holds the list of Tasks.
     * @param ui Ui object that displays messages to user based on action taken by chatbot.
     * @param storage Storage object that saves changes to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * {@inheritDoc}
     *
     * @return true as user wishes to exit the chatbot.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

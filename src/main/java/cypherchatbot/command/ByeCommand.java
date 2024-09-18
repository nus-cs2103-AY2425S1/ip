package cypherchatbot.command;

import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;

/**
 * Represents the command to exit the chatbot.
 * This command signals that the user wants to close the application.
 */
public class ByeCommand extends Command {

    /**
     * Executes the ByeCommand by showing a goodbye message to the user.
     *
     * @param tasks   The TaskList containing all the tasks.
     * @param ui      The Ui interface used to interact with the user.
     * @param storage The Storage file where the task data will be saved.
     * @return returns the goodbye message to be displayed to the User
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.goodBye();
    }

    @Override
    public boolean showExitStatus() {
        return true;
    }
}

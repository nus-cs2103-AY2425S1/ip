package alex.command;

import alex.Storage;
import alex.Ui;
import alex.task.TaskList;

/**
 * Represents the command by user to exit the chatbot.
 */
public class ExitCommand extends Command {

    /**
     * {@inheritDoc}
     *
     * Displays the goodbye message to the user.
     *
     * @param tasks TaskList that holds the list of Tasks.
     * @param ui Ui object that displays messages to the user based on the action taken by the chatbot.
     * @param storage Storage object that saves changes to the file.
     * @return A goodbye message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }

    @Override
    public String getCommandType() {
        return "ExitCommand";
    }
}


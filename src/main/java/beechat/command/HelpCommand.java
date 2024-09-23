package beechat.command;

import beechat.task.TaskList;
import beechat.util.Storage;
import beechat.util.Ui;

/**
 * Represents a command to display the help message.
 *
 */
public class HelpCommand extends Command {

    /**
     * Executes the help command and displays the help message.
     *
     * @param tasks   The TaskList object that contains all tasks.
     * @param ui      The Ui object that handles all user interactions.
     * @param storage The Storage object that saves and loads tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelp();
    }

    /**
     * Indicates that this is a leave command.
     *
     * @return {@code true} since this is a leave command.
     */
    @Override
    public boolean isLeave() {
        return true;
    }
}
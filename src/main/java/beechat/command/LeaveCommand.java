package beechat.command;

import beechat.util.Storage;
import beechat.task.TaskList;
import beechat.util.Ui;

import java.io.IOException;

/**
 * Represents a command to leave the chatbot application.
 *
 */
public class LeaveCommand extends Command {

    /**
     * Executes the leave command and displays the bye message.
     *
     * @param tasks   The TaskList object that contains all tasks.
     * @param ui      The Ui object that handles all user interactions.
     * @param storage The Storage object that saves and loads tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
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
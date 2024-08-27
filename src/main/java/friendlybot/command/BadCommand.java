package friendlybot.command;

import friendlybot.Storage;
import friendlybot.Ui;
import friendlybot.task.TaskList;

/**
 * BadCommand is a Command that does nothing when executed. BadCommand is used when the user's input does not
 * correspond with any methods.
 */
public class BadCommand extends Command {
    /**
     * Does nothing upon execution.
     *
     * @param tasks An instance of TaskList where the new task is added to.
     * @param ui An instance of Ui (User Interface) that handles the interactions between FriendlyBot and user.
     * @param storage An instance of Storage that loads tasks and saves tasks in a file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        return;
    }
}

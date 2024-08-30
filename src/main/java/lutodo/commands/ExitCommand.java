package lutodo.commands;

import lutodo.tasklist.TaskList;
import lutodo.storage.Storage;
import lutodo.ui.Ui;

/**
 * Represents the command of exiting the chat box.
 */
public class ExitCommand extends Command{


    /**
     * Shows bye to the user and exits.
     *
     * @param tasks The TaskList the method interacts with.
     * @param storage The Storage object used to save the new task list.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        Ui.showBye();
    }

    /**
     * Returns true since this type of command is the exit command.
     *
     * @return whether this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Represents the command with a String containing basic information of this command.
     *
     * @return a String containing basic information of this command.
     */
    @Override
    public String toString() {
        return "ExitCommand";
    }
}

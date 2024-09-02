package friendlybot.command;

import friendlybot.Storage;
import friendlybot.Ui;
import friendlybot.task.TaskList;

/**
 * ExitCommand is a Command that saves the tasks to a file, and exits upon execution.
 */
public class ExitCommand extends Command {
    /**
     * Saves tasks to file and prints an exit message upon execution.
     *
     * @param tasks An instance of TaskList where the new task is added to.
     * @param ui An instance of Ui (User Interface) that handles the interactions between FriendlyBot and user.
     * @param storage An instance of Storage that loads tasks and saves tasks in a file.
     *
     * @return Returns a response String from FriendlyBot to the User.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.writeToFile(tasks.formatTasksToSave());
        return ui.exitMessage();
    }

    /**
     * Tells FriendlyBot that the user wishes to exit the program.
     *
     * @return True
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

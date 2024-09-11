package thebotfather.command;

import thebotfather.util.Storage;
import thebotfather.util.TaskList;
import thebotfather.util.TheBotFatherException;
import thebotfather.util.Ui;

/**
 * A command that terminates the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by saving the current task list to storage and printing a goodbye message.
     *
     * @param taskList The current task list that should be saved before exiting.
     * @param ui The user interface to display the goodbye message.
     * @param storage The storage system responsible for saving the task list.
     * @return A string containing the goodbye message.
     * @throws TheBotFatherException If an error occurs while saving the task list to storage.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException {
        assert taskList != null : "Task list cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        storage.toFile(taskList);
        return ui.getGoodBye();
    }

    /**
     * Determines whether this command will cause the application to terminate.
     *
     * @return {@code true}, since this command exits the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

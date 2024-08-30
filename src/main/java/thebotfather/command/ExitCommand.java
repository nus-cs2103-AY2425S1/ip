package thebotfather.command;

import thebotfather.util.Storage;
import thebotfather.util.TaskList;
import thebotfather.util.TheBotFatherException;
import thebotfather.util.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by printing a goodbye message and saving the current task list to storage.
     *
     * @param taskList The task list to be saved before exiting.
     * @param ui The user interface used to print the goodbye message.
     * @param storage The storage system used to save the task list.
     * @throws TheBotFatherException If an error occurs while saving the task list to storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException {
        ui.printGoodBye();
        storage.toFile(taskList);
    }

    /**
     * Indicates whether this command causes the application to exit.
     *
     * @return {@code true} since this command exits the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

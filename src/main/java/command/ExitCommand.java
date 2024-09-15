package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command that signals the termination of the application.
 * The {@code ExitCommand} is used to indicate that the application should exit.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     * This method does not perform any action as its primary purpose is to signal
     * that the application should exit.
     *
     * @param taskList The task list, which is not used in this command.
     * @param ui       The user interface object, which is not used in this command.
     * @param storage  The storage object, which is not used in this command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return "";
    }

    /**
     * Indicates whether this command is an exit command.
     *
     * @return True, as this command signifies that the application should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

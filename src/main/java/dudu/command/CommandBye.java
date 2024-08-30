package dudu.command;

import dudu.command.Command;
import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

public class CommandBye extends Command {
    /**
     * Executes the exit command by displaying a goodbye message to the user.
     *
     * @param taskList The task list (not used in this command).
     * @param ui The user interface to display the goodbye message.
     * @param storage The storage (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.goodbyeMessage();
    }

    /**
     * Indicates that this command will cause the application to exit.
     *
     * @return true, as this command always causes the application to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

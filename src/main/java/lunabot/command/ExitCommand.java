package lunabot.command;

import lunabot.exception.LunaBotException;
import lunabot.storage.Storage;
import lunabot.task.TaskList;
import lunabot.ui.Ui;

/**
 * Command to exit the LunaBot chat.
 */
public class ExitCommand extends Command {

    /**
     * @param taskList Current list of tasks in the taskList.
     * @param ui User interface that handles user input interactions and display messages.
     * @param storage Storage system to save or load tasks to/from a file.
     * @throws LunaBotException If an error occurs while executing the command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws LunaBotException {
        ui.printGoodbye();
    }

}

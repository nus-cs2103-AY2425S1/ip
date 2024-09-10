package LunaBot.command;

import LunaBot.exception.LunaBotException;
import LunaBot.storage.Storage;
import LunaBot.task.TaskList;
import LunaBot.ui.Ui;

/**
 * Command to list out all tasks in the taskList.
 */
public class ListCommand extends Command {

    /**
     * @param taskList Current list of tasks for command to list out.
     * @param ui User interface that handles user input interactions and display messages.
     * @param storage Storage system to save or load tasks to/from a file.
     * @throws LunaBotException If an error occurs while executing the command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws LunaBotException {
        ui.printTaskList(taskList.getTasks());
    }
}

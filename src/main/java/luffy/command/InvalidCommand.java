package luffy.command;

import luffy.storage.Storage;
import luffy.task.TaskList;
import luffy.ui.LuffyUI;

/**
 * Represents a command that informs user their input command is invalid
 */

public class InvalidCommand extends Command {

    /**
     * This method adds an executable command to inform user
     * that their input is invalid by the UI
     *
     * @param ui user interface for Chat Bot
     * @param taskStorage storage location for file
     * @param taskList array list of existing tasks
     */
    @Override
    public void executeCmd(LuffyUI ui, Storage taskStorage, TaskList taskList) {
        ui.showErrorMessage("Invalid Command.");

    }
}

package luffy.command;

import luffy.storage.Storage;
import luffy.task.TaskList;
import luffy.ui.LuffyUI;

/**
 * Represents a command that exits the chatbot and ends interaction
 */

public class ExitCommand extends Command {

    /**
     * This method adds an executable command to exit the
     * chatbot by the UI
     *
     * @param ui user interface for Chat Bot
     * @param taskStorage storage location for file
     * @param taskList array list of existing tasks
     */
    @Override
    public void executeCmd(LuffyUI ui, Storage taskStorage, TaskList taskList) {
        ui.showExitMessage();
        System.exit(0);
    }

}

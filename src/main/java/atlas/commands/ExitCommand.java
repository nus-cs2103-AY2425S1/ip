package atlas.commands;

import atlas.storage.Storage;
import atlas.tasks.TaskList;
import atlas.ui.Ui;

/**
 * Exits the chatbot when this class is instantiated.
 */
public class ExitCommand extends Command {
    /**
     * @param tasks The current list of tasks in the chatbot.
     * @param ui The current ui object the chatbot uses to display messages
     * @param storage The storage object the chatbot uses to store and load tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        super.setIsExit();
        ui.exit();
    }
}

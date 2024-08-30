package atlas.commands;

import atlas.storage.Storage;
import atlas.tasks.TaskList;
import atlas.ui.Ui;

public class ExitCommand extends Command {
    /**
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        super.setIsExit();
        ui.exit();
    }
}

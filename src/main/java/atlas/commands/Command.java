package atlas.commands;

import atlas.exceptions.AtlasException;
import atlas.storage.Storage;
import atlas.tasks.TaskList;
import atlas.ui.Ui;

public abstract class Command {
    private boolean isExit = false;

    /**
     * @param tasks
     * @param ui
     * @param storage
     * @throws AtlasException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AtlasException;

    /**
     * @return
     */
    public boolean isExit() {
        return this.isExit;
    }

    public void setIsExit() {
        this.isExit = true;
    }
}
package Nah.Command;

import Nah.Exceptions.*;
import Nah.TaskList.TaskList;
import Nah.UI.UI;
import Nah.Storage.Storage;
public abstract class Command {
    /**
     * return true if this is exit command
     * @return
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Execute command with corresponding Nah.TaskList, Nah.UI and Nah.Storage
     * @param task
     * @param ui
     * @param storage
     */
    public abstract void execute(TaskList task, UI ui, Storage storage) throws NahException;
}

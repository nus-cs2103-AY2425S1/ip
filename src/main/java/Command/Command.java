package Command;

import Exceptions.*;
import TaskList.TaskList;
import UI.UI;
import Storage.Storage;
public abstract class Command {
    /**
     * return true if this is exit command
     * @return
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Execute command with corresponding TaskList, UI and Storage
     * @param task
     * @param ui
     * @param storage
     */
    public abstract void execute(TaskList task, UI ui, Storage storage) throws NahException;
}

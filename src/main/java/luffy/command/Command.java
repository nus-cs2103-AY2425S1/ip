package luffy.command;

import luffy.storage.Storage;
import luffy.task.TaskList;
import luffy.ui.LuffyUI;


/**
 * Represents a command that depends on user input
 */
public abstract class Command {
    public abstract void executeCmd(LuffyUI ui, Storage taskStorage, TaskList taskList);
}

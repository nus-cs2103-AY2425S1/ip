package commands.undo.managers;

import commands.Command;
import commands.MarkCommand;
import commands.UnmarkCommand;
import exceptions.BrockException;
import storage.task.TaskStorage;
import storage.temp.TempStorage;
import task.TaskList;

/**
 * Class to undo previous command, if it is a mark/unmark command.
 */
public class PrevToggleManager extends PrevCommandManager {
    /**
     * {@inheritDoc}
     *
     * Specifically, undoes the previous mark/unmark command.
     */
    @Override
    public String undoPrevCommand(TaskStorage taskStorage, TempStorage tempStorage, TaskList tasks)
            throws BrockException {
        int lastToggledTaskNum = tempStorage.getLastToggledTaskNum();
        Command toggleCommand;
        if (PrevCommandManager.isMark) {
            toggleCommand = new UnmarkCommand("unmark " + lastToggledTaskNum);
        } else {
            toggleCommand = new MarkCommand("mark " + lastToggledTaskNum);
        }
        return toggleCommand.execute(taskStorage, tempStorage, tasks);
    }
}

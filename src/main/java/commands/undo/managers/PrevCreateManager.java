package commands.undo.managers;

import commands.Command;
import commands.DeleteCommand;
import exceptions.BrockException;
import storage.task.TaskStorage;
import storage.temp.TempStorage;
import task.TaskList;

/**
 * Class to undo previous command, if it is a todo/deadline/event command.
 */
public class PrevCreateManager extends PrevCommandManager {
    /**
     * {@inheritDoc}
     *
     * Specifically, undoes the previous todo/deadline/event command.
     */
    @Override
    public String undoPrevCommand(TaskStorage taskStorage, TempStorage tempStorage, TaskList tasks)
            throws BrockException {
        int lastCreatedTaskNum = tempStorage.getLastCreatedTaskNum();
        Command deleteCommand = new DeleteCommand("delete " + lastCreatedTaskNum);
        return deleteCommand.execute(taskStorage, tempStorage, tasks);
    }
}

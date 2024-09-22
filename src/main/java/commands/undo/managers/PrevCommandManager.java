package commands.undo.managers;

import exceptions.BrockException;
import storage.task.TaskStorage;
import storage.temp.TempStorage;
import task.TaskList;

/**
 * Abstract base class to provide a template for a previous command manager.
 */
public abstract class PrevCommandManager {
    protected static boolean isMark = false;

    /**
     * Undo the previous command.
     *
     * @param taskStorage {@code TaskStorage} object that creates and interfaces with save file.
     * @param tempStorage {@code TempStorage} object that stores info required to undo previous valid command.
     * @param tasks {@code TaskList} object that stores the current tasks in an {@code ArrayList}.
     * @return Returns the string response associated with undoing the previous command.
     * @throws BrockException If there is any issues with undoing.
     */
    public abstract String undoPrevCommand(TaskStorage taskStorage, TempStorage tempStorage, TaskList tasks)
            throws BrockException;

    public void setIsMark(boolean isMark) {
        PrevCommandManager.isMark = isMark;
    }
}

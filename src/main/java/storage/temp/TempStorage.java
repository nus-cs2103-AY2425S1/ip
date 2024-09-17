package storage.temp;

import task.Task;

/**
 * A class that stores information required to undo the last valid command.
 */
public class TempStorage {
    private static String previousValidCommand;
    private static int lastCreatedTaskNum;
    private static int lastToggledTaskNum;
    private static Task lastDeletedTask;

    /**
     * Retrieves the previous valid command executed.
     *
     * @return the previous valid command as a String.
     */
    public String getPreviousCommand() {
        return TempStorage.previousValidCommand;
    }

    /**
     * Sets the previous valid command executed.
     *
     * @param previousCommand the valid command to be set as the previous valid command.
     */
    public void setPreviousCommand(String previousCommand) {
        TempStorage.previousValidCommand = previousCommand;
    }

    /**
     * Retrieves the task number of the last created task.
     *
     * @return the task number of the last created task as an integer.
     */
    public int getLastCreatedTaskNum() {
        return TempStorage.lastCreatedTaskNum;
    }

    /**
     * Sets the task number of the last created task.
     *
     * @param lastCreatedTaskNum the task number of the last created task.
     */
    public void setLastCreatedTaskNum(int lastCreatedTaskNum) {
        TempStorage.lastCreatedTaskNum = lastCreatedTaskNum;
    }

    /**
     * Retrieves the task number of the last toggled task.
     *
     * @return the task number of the last toggled task as an integer.
     */
    public int getLastToggledTaskNum() {
        return TempStorage.lastToggledTaskNum;
    }

    /**
     * Sets the task number of the last toggled task.
     *
     * @param lastToggledTaskNum the task number of the last toggled task.
     */
    public void setLastToggledTaskNum(int lastToggledTaskNum) {
        TempStorage.lastToggledTaskNum = lastToggledTaskNum;
    }

    /**
     * Retrieves the last deleted task.
     *
     * @return the last deleted task as a {@link Task}.
     */
    public Task getLastDeletedTask() {
        return TempStorage.lastDeletedTask;
    }

    /**
     * Sets the last deleted task.
     *
     * @param lastDeletedTask the task to be set as the last deleted task.
     */
    public void setLastDeletedTask(Task lastDeletedTask) {
        TempStorage.lastDeletedTask = lastDeletedTask;
    }
}

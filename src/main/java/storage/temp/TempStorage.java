package storage.temp;

import task.Task;

public class TempStorage {
    private static String previousCommand;
    private static int lastCreatedTaskNum;
    private static int lastToggledTaskNum;
    private static Task lastDeletedTask;

    public String getPreviousCommand() {
        return TempStorage.previousCommand;
    }
    public void setPreviousCommand(String previousCommand) {
        TempStorage.previousCommand = previousCommand;
    }

    public int getLastCreatedTaskNum() {
        return TempStorage.lastCreatedTaskNum;
    }
    public void setLastCreatedTaskNum(int lastCreatedTaskNum) {
        TempStorage.lastCreatedTaskNum = lastCreatedTaskNum;
    }

    public int getLastToggledTaskNum() {
        return TempStorage.lastToggledTaskNum;
    }
    public void setLastToggledTaskNum(int lastToggledTaskNum) {
        TempStorage.lastToggledTaskNum = lastToggledTaskNum;
    }

    public Task getLastDeletedTask() {
        return TempStorage.lastDeletedTask;
    }
    public void setLastDeletedTask(Task lastDeletedTask) {
        TempStorage.lastDeletedTask = lastDeletedTask;
    }
}

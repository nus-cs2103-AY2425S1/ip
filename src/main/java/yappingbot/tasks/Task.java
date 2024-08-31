package yappingbot.tasks;

import yappingbot.exceptions.YappingBotInvalidSaveFileException;

import static yappingbot.stringconstants.ReplyTextMessages.INVALID_SAVE_FILE_EXCEPTION_MISSING_VALUES;

public abstract class Task {
    private String taskName;
    private boolean taskDone;
    private TaskTypes taskType;

    public Task() {
        this("Untitled", false);
    }

    public Task(String taskName, boolean taskDone) {
        this.taskName = taskName;
        this.taskDone = taskDone;
    }

    public TaskTypes getTaskType() {
        return taskType;
    }

    protected void setTaskType(TaskTypes taskType) {
        this.taskType = taskType;
    }


    public void setTaskDone(boolean taskDone) {
        this.taskDone = taskDone;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isTaskDone() {
        return taskDone;
    }

    public abstract String getTaskTypeSymbol();

    public String getTaskDoneCheckmark() {
        if (this.taskDone) {
            return "X";
        } else {
            return " ";
        }
    }

    public String getTaskName() {
        return taskName;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Completed: %s", this.taskName, this.taskDone);
    }

    public String serialize() {
        return String.format("%s:%s:%s",
                taskType,
                taskName.replaceAll(":", "/colon"),
                taskDone
        );
    }

    public void deserialize(String[] stringDataSlices) throws YappingBotInvalidSaveFileException {
        if (stringDataSlices.length < 3) {
            throw new YappingBotInvalidSaveFileException(
                    INVALID_SAVE_FILE_EXCEPTION_MISSING_VALUES
            );
        }
        try {
            taskType = TaskTypes.valueOf(stringDataSlices[0]);
            taskName = stringDataSlices[1].replaceAll("/colon", ":");
            taskDone = Boolean.parseBoolean(stringDataSlices[2]);
        } catch (IllegalArgumentException e) {
            throw new YappingBotInvalidSaveFileException(e.getMessage());
        }
    }
}


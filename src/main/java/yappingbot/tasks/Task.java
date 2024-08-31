package yappingbot.tasks;

import yappingbot.exceptions.YappingBotInvalidSaveFileException;
import yappingbot.tasks.tasklist.TaskTypes;

import static yappingbot.stringconstants.ReplyTextMessages.INVALID_SAVE_FILE_EXCEPTION_MISSING_VALUES;

public abstract class Task {
    private String taskName;
    private boolean taskDone;
    private TaskTypes taskType;

    public Task() {
        this("Untitled", false);
    }

    public TaskTypes getTaskType() {
        return taskType;
    }
    protected void setTaskType(TaskTypes taskType) {
        this.taskType = taskType;
    }
    public Task(String taskName, boolean taskDone) {
        this.taskName = taskName;
        this.taskDone = taskDone;
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
    public void deserialize(String[] sString) throws YappingBotInvalidSaveFileException {
        if (sString.length < 3) {
            throw new YappingBotInvalidSaveFileException(INVALID_SAVE_FILE_EXCEPTION_MISSING_VALUES);
        }
        try {
            taskType = TaskTypes.valueOf(sString[0]);
            taskName = sString[1].replaceAll("/colon", ":");
            taskDone = Boolean.parseBoolean(sString[2]);
        } catch (IllegalArgumentException e) {
            throw new YappingBotInvalidSaveFileException(e.getMessage());
        }
    }

    /**
     * Returns if the given String is found within anywhere in the task.
     *
     * @param searchString String to be searched in the Task.
     * @return boolean True if found, else false.
     */
    public boolean isStringFoundInTask(String searchString) {
        // Prevent passing null or empty string
        if (searchString == null || searchString.isEmpty()) {
            return false;
        }

        return this.taskName.contains(searchString);
    }
}


package yappingbot.tasks;

import static yappingbot.stringconstants.ReplyTextMessages.INVALID_SAVE_FILE_EXCEPTION_MISSING_VALUES;

import yappingbot.exceptions.YappingBotInvalidSaveFileException;
import yappingbot.tasks.tasklist.TaskTypes;

/**
 * Abstract Task class for the possible Task variants.
 */
public abstract class Task {
    private String taskName;
    private boolean isTaskDone;
    private TaskTypes taskType;

    /**
     * Constructor for a blank task (Task name "Untitled", unmarked).
     */
    public Task() {
        this("Untitled", false);
    }

    /**
     * Creates a task.
     *
     * @param taskName String name of this task.
     * @param isTaskDone Boolean of whether the task is marked or unmarked as done.
     */
    public Task(String taskName, boolean isTaskDone) {
        assert taskName != null;
        this.taskName = taskName;
        this.isTaskDone = isTaskDone;
    }

    /**
     * Gets the type of task, depending on which subclass extends this.
     *
     * @return TaskTypes type of task.
     */
    public TaskTypes getTaskType() {
        assert  taskType != null;
        return taskType;
    }


    /**
     * Sets the type of task. To only be set internally by different task classes that extend this.
     *
     * @param taskType TaskType of the task.
     */
    protected void setTaskType(TaskTypes taskType) {
        this.taskType = taskType;
    }

    public void setTaskDone(boolean taskDone) {
        this.isTaskDone = taskDone;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isTaskDone() {
        return isTaskDone;
    }

    public String getTaskName() {
        return taskName;
    }

    /**
     * Provides a friendly version of the task type, in the form of a single-char String.
     *
     * @return String of a single char, demarcating what the task type is in a friendly manner.
     */
    public abstract String getTaskTypeSymbol();


    /**
     * Returns "X" or " " depending on whether task is marked done.
     *
     * @return String "X" or " " depending on whether task is marked done.
     */
    public String getTaskDoneCheckmark() {
        if (this.isTaskDone) {
            return "X";
        } else {
            return " ";
        }
    }

    @Override
    public String toString() {
        assert taskName != null;
        return String.format("Name: %s, Completed: %s", this.taskName, this.isTaskDone);
    }


    /**
     * Serializes the task in a String that can then be used to save the task to disk.
     * Escapes any colons (:) before formatting the task as colon-separated values.
     *
     * @return String of serialized format of the task.
     */
    public String serialize() {
        assert taskName != null;
        assert taskType != null;
        return String.format("%s:%s:%s",
                taskType,
                taskName.replaceAll(":", "/colon"),
                isTaskDone
        );
    }

    /**
     * Deserializes the String Slices and populates the task to the appropriate values.
     * Replaces any escaped colons (:).
     * Required format: "TaskType:TaskName:isTaskDone"
     * Subclasses may include additional values (eg dates) that will be required accordingly.
     *
     * @param stringDataSlices String array, split by colons.
     * @throws YappingBotInvalidSaveFileException Exception if any issues parsing the given
     *         String arrays.
     */
    public void deserialize(String[] stringDataSlices) throws YappingBotInvalidSaveFileException {
        assert stringDataSlices != null;
        if (stringDataSlices.length < 3) {
            throw new YappingBotInvalidSaveFileException(
                    INVALID_SAVE_FILE_EXCEPTION_MISSING_VALUES
            );
        }
        try {
            taskType = TaskTypes.valueOf(stringDataSlices[0]);
            taskName = stringDataSlices[1].replaceAll("/colon", ":");
            isTaskDone = Boolean.parseBoolean(stringDataSlices[2]);
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


package luffy;

/**
 * Represents a task that the chatbot user intends to
 * note down on
 */
public class Task {

    private final String taskInfo;
    private boolean isDone;

    /**
     * Constructor to create a task
     * @param taskInfo task that the user wants to store
     * @param isDone state of task
     */
    public Task(String taskInfo, boolean isDone) {
        this.taskInfo = taskInfo;
        this.isDone = isDone;
    }

    /**
     * This method adds changes the task from undone to done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * This method adds changes the task from done to undone
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns the state of completeness of the task
     *
     * @return state of task
     */
    public boolean checkIsDone() {
        return this.isDone;
    }

    /**
     * Returns the string representation of the task given its state
     *
     * @return string of task given its state
     */
    public String stringIsDone() {
        if (this.isDone) {
            return String.format("[X] %s", this.taskInfo);
        } else {
            return String.format("[ ] %s", this.taskInfo);
        }
    }

    /**
     * Returns the action of the task
     *
     * @return action of task
     */
    public String getTaskInfo() {
        return this.taskInfo;
    }

    /**
     * Returns the state of completeness of the task to be saved in the file
     *
     * @return formatted string of task given its state
     */
    public String dataToSave() {
        if (this.isDone) {
            return String.format("DONE: %s", this.taskInfo);
        } else {
            return String.format("UNDONE: %s", this.taskInfo);
        }
    }
}

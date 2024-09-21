package nimbus.task;

/**
 * Task that has a name and a boolean variable to check if it is completed
 */
public class Task {
    private String taskName;
    private boolean isComplete;

    /**
     * Constructor with default incomplete status for Task
     *
     * @param taskName
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isComplete = false;
    }

    /**
     * Constructor with default completed status for Task
     *
     * @param taskName
     */
    public Task(String taskName, boolean isComplete) {
        this.taskName = taskName;
        this.isComplete = isComplete;
    }

    /**
     * Changes the boolean value of isComplete to true
     */
    public void setComplete() {
        this.isComplete = true;
    }

    /**
     * Changes the boolean value of isComplete to false
     */
    public void setIncomplete() {
        this.isComplete = false;
    }

    /**
     * returns the boolean value of isComplete
     *
     * @return boolean value of isComplete
     */
    public boolean isComplete() {
        return this.isComplete;
    }



    /**
     * Gets task name
     *
     * @return String of task name
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Changes the string format to file format to store the task in the text file
     *
     * @return a string to be stored in text file
     */
    public String toFileFormat() {
        return this.taskName;
    }

    @Override
    public String toString() {
        if (this.isComplete) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }
}

package slave;

public abstract class Task {
    private boolean isCompleted;
    private String task;

    public Task(String task) {
        this.isCompleted = false;
        this.task = task;
    }

    protected Task(boolean completed, String task) {
        this.task = task;
        this.isCompleted = completed;
    }

    /**
     * @return the boolean value stored in the task
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * @return the string description of the task
     */
    public String getTask() {
        return this.task;
    }

    /**
     * sets the task as completed
     */
    public void setAsCompleted() {
        this.isCompleted = true;
    }

    /**
     * sets the task as incomplete
     */
    public void setAsIncomplete() {
        this.isCompleted = false;
    }

    /**
     * Prints the task in the required format
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isCompleted()) {
            sb.append("[X] ");
        } else {
            sb.append("[] ");
        }
        sb.append(getTask());
        return sb.toString();
    }

    /**
     * used as a .toString() used only when save() is called in Slave.java
     * prints the Task's date in the format yyyy-mm-dd
     *
     * @return a String representation of the task
     */
    abstract String save();
}

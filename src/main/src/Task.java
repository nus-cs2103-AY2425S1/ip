public abstract class Task {
    private boolean completed;
    private String task;

    public Task(String task) {
        this.completed = false;
        this.task = task;
    }

    /**
     *
     * @return the boolean value stored in the task
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     *
     * @return the string description of the task
     */
    public String getTask() {
        return this.task;
    }

    /**
     * sets the task as completed
     */
    public void completed() {
        this.completed = true;
    }

    /**
     * sets the task as incomplete
     */
    public void incomplete() {
        this.completed = false;
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
}

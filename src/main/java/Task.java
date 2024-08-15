public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Sets the completion status of the task.
     * @param status The status to change to
     */
    public void setCompletionStatus(boolean status) {
        this.isDone = status;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
                isDone ? "X" : " ",
                name);
    }

}

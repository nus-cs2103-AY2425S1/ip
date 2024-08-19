abstract class Task {
    private final String taskDescription;
    private boolean isDone;

    public Task(String taskDescription, boolean isDone) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    public Task(String taskDescription) {
        this(taskDescription, false);
    }

    private boolean isDone() {
        return this.isDone;
    }

    private void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.setDone(true);
    }

    public void unmarkAsDone() {
        this.setDone(false);
    }

    @Override
    public String toString() {
        return "[" + (this.isDone() ? "X" : " ") + "] " + taskDescription;
    }
}

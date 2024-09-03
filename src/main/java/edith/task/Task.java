package edith.task;

public abstract class Task {
    private String taskName;
    private boolean isCompleted; // false for undone, true for done

    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    public void check() { // mark task as completed
        this.isCompleted = true;
    }

    public void uncheck() { // unmark task as completed
        this.isCompleted = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean getCompletionStatus() {
        return this.isCompleted;
    }

    @Override
    public String toString() {
        if (this.isCompleted) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }

    public abstract String convertToFileFormat();
}

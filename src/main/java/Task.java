public abstract class Task {
    private String taskName;
    private boolean status; // false for undone, true for done

    public Task(String taskName) {
        this.taskName = taskName;
        this.status = false;
    }

    public void check() { // mark task as completed
        this.status = true;
    }

    public void uncheck() { // unmark task as completed
        this.status = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        if (this.status) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }
}

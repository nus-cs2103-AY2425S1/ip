public class Task {
    private final String taskName;
    private boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    public void doTask() {
        this.done = true;
    }

    public void undoTask() {
        this.done = false;
    }

    public boolean isDone() {
        return this.done;
    }

    public String getTaskName() {
        return this.taskName;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + taskName;
        }
        return "[ ] " + taskName;
    }
}

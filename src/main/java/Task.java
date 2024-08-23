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

    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + taskName;
        }
        return "[ ] " + taskName;
    }
}

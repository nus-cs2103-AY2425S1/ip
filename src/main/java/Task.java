public class Task {
    private final String taskName;
    private boolean completed;
    public Task(String taskName) {
        this.taskName = taskName;
        completed = false;
    }

    public void markAsCompleted() {
        completed = true;
    }

    public void markAsUncompleted() {
        completed = false;
    }

    @Override
    public String toString() {
        String status = "";
        if (completed) {
            status = "[X] ";
        } else {
            status = "[ ] ";
        }
        return status + taskName;
    }
}

public class Task {
    private final String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return this.taskName;
    }

    @Override
    public String toString() {
        return this.taskName;
    }
}

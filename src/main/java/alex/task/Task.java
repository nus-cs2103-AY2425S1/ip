package alex.task;

public class Task {
    private String taskName;

    private boolean isCompleted;
    public Task(String taskName, boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    public void markAsDone() {
        this.isCompleted = true;
    }

    public void markAsUndone() {
        this.isCompleted = false;
    }
    @Override
    public String toString() {
        String box = "";
        if (this.isCompleted) {
            box = "[X]";
        } else {
            box = "[ ]";
        }

        return String.format("%s %s", box, this.taskName);
    }

    public String toStorageString() {
        return "";
    }
}

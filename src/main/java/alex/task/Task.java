package alex.task;

public class Task {
    private String taskName;

    private boolean taskCompletionStatus;
    public Task(String taskName, boolean taskCompletionStatus) {
        this.taskName = taskName;
        this.taskCompletionStatus = taskCompletionStatus;
    }

    public void markAsDone() {
        this.taskCompletionStatus = true;
    }

    public void markAsUndone() {
        this.taskCompletionStatus = false;
    }
    @Override
    public String toString() {
        String box = "";
        if (this.taskCompletionStatus) {
            box = "[X]";
        } else {
            box = "[ ]";
        }

        return String.format("%s %s", box, this.taskName);
    }

    public String storageString() {
        return "";
    }

    public boolean hasSearchString(String searchString) {
        return this.taskName.contains(searchString);
    }
}

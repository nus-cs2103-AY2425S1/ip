public class Task {
    private String taskDescription;
    private boolean doneStatus;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.doneStatus = false;
    }

    public void markAsDone() {
        this.doneStatus = true;
    }

    public void markAsUndone() {
        this.doneStatus = false;
    }

    @Override
    public String toString() {
        if (this.doneStatus) {
            return "[X] " + taskDescription;
        } else {
            return "[ ] " + taskDescription;
        }
    }

    public String toSaveRepresentation() {
        if (this.doneStatus) {
            return "1 | " + taskDescription;
        } else {
            return "0 | " + taskDescription;
        }
    }
}

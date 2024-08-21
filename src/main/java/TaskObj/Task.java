package TaskObj;
public class Task {
    public static int taskNumber = 0;
    private final String description;
    private Boolean completed = false;

    public Task(String desc) {
        this.description = desc;
        taskNumber++;
    }

    public void mark() {
        if (!this.completed) {
            this.completed = true;
        }
    }

    public void unmark() {
        if (this.completed) {
            this.completed = false;
        }
    }

    @Override
    public String toString() {
        return completed ? "[X] " + description : "[ ] " + description;
    }
}

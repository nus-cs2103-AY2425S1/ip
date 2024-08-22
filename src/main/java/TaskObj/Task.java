package TaskObj;
public class Task {
    public static int taskNumber = 0;
    private final String description;
    private Boolean completed = false;

    private Boolean deleted = false;

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

    public void delete() {
        this.deleted = true;
        taskNumber--;
    }

    @Override
    public String toString() {
        return completed ? "[X] " + description : "[ ] " + description;
    }
}

package TaskObj;
public class Task {
    public static int taskNumber = 0;
    private final String description;
    private Boolean isCompleted = false;

    private Boolean deleted = false;

    public Task(String desc) {
        this.description = desc;
        taskNumber++;
    }

    public Task(String desc, Boolean isCompleted) {
        this.description = desc;
        this.isCompleted = isCompleted;
        taskNumber++;
    }

    public void mark() {
        if (!this.isCompleted) {
            this.isCompleted = true;
        }
    }

    public void unmark() {
        if (this.isCompleted) {
            this.isCompleted = false;
        }
    }

    public void delete() {
        this.deleted = true;
        taskNumber--;
    }

    @Override
    public String toString() {
        return isCompleted ? "[X] " + description : "[ ] " + description;
    }

    public String toTextString() {
        return isCompleted ? " | 1 | " + description : " | 0 | " + description;
    }
}

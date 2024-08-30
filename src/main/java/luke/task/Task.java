package luke.task;

public abstract class Task {
    protected String name;
    protected Boolean isDone;

    public Task(String taskName, boolean isDone) {
        this.name = taskName;
        this.isDone = isDone;
    }

    public void changeMark() {
        this.isDone = !isDone;
    }

    public String showMark() {
        return isDone
                ? "[X]"
                : "[ ]";
    }

    public abstract String taskDescription();
    public abstract String taskInSaveData();
}
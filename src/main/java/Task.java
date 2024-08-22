public abstract class Task {
    protected String name;
    protected Boolean isDone;

    public Task(String taskName) {
        this.name = taskName;
        this.isDone = false;
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
}
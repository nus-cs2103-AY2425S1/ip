package bottle.task;

public abstract class Task {

    protected boolean isChecked;
    protected String taskDesc;

    public Task(String taskDesc) {
        this.taskDesc = taskDesc;
        this.isChecked = false;
    }
    public void mark() {
        this.isChecked = true;
    }

    public void unMark() {
        this.isChecked = false;
    }

    @Override
    public String toString() {
        String box = isChecked ? "[X] " : "[ ] ";
        return box + taskDesc;
    }
    public abstract String toSaveFormat();
}

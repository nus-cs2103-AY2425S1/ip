public class Task {
    private boolean status;
    private String taskInfo;

    public Task(String taskInfo) {
        this.taskInfo = taskInfo;
        this.status = false;
    }

    public void markDone() {
        this.status = true;
    }

    public void markUndone() {
        this.status = false;
    }

    public boolean isDone() {
        return this.status;
    }

    public String getTaskInfo() {
        return taskInfo;
    }

    @Override
    public String toString() {
        String s = "";
        if (this.isDone()) {
            s += "[X] ";
        } else {
            s += "[ ] ";
        }
        s += this.taskInfo;
        return s;
    }
}

public class Task {

    private final String taskInfo;
    private boolean isDone;

    public Task(String taskInfo, boolean isDone) {
        this.taskInfo = taskInfo;
        this.isDone = isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public boolean checkIsDone() {
        return this.isDone;
    }

    public String stringIsDone() {
        if (this.isDone) {
            return String.format("[X] %s", this.taskInfo);
        } else {
            return String.format("[ ] %s", this.taskInfo);
        }
    }

    public String getTaskInfo() {
        return this.taskInfo;
    }

    public String dataToSave() {
        if (this.isDone) {
            return String.format("DONE: %s", this.taskInfo);
        } else {
            return String.format("UNDONE: %s", this.taskInfo);
        }
    }
}

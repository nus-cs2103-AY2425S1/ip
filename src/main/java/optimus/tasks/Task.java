package optimus.tasks;

public class Task {
    private boolean isDone;
    private final String taskDesc;

    public Task(String desc) {
        this.isDone = false;
        this.taskDesc = desc;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void markAsComplete() {
        this.isDone = true;
    }

    public void markAsIncomplete() {
        this.isDone = false;
    }

    public String getStatusString() {
        return this.isDone ? "X" : " ";
    }

    public int getStatusInt() {
        return this.isDone ? 1 : 0;
    }

    public String getStorageString() {
        return "";
    }
}

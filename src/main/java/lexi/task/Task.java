package lexi.task;

public class Task {
    private boolean isDone;
    private final String taskName;

    public Task(String taskName){
        this.isDone = false;
        this.taskName = taskName;
    }
    public void setDone(boolean value) {
        this.isDone = value;
    }
    public boolean getIsDone() {
        return this.isDone;
    }
    public String getTaskName() {
        return this.taskName;
    }
    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return "[" + status + "] " + this.taskName;
    }

}

public class Task {
    private boolean isDone;
    private final String taskName;

    public Task(String taskName){
        this.isDone = false;
        this.taskName = taskName;
    }
    public void doTask() {
        this.isDone = true;
    }
    public void undoTask() {
        this.isDone = false;
    }
    public boolean getIsDone() {
        return this.isDone;
    }
    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return "[" + status + "] " + this.taskName;
    }

}

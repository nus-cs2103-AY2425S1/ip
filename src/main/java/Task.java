public class Task {
    private String taskName;
    private boolean isDone;
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }
    public void setDone(Boolean bool) {
        this.isDone = bool;
    }
    public String getTaskName() {
        return this.taskName;
    }
    public boolean isDone() {
        return this.isDone;
    }
    public String getCompetionChar() {
        return isDone ? "X" : " ";
    }

    public String toString() {
        return "[" + getCompetionChar() + "] " + taskName;
    }
    

}

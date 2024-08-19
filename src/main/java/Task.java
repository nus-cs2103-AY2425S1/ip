public class Task {
    public String taskDescription;
    public Boolean isDone;

    public Task(String taskDescription, Boolean isDone) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public Boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }
}
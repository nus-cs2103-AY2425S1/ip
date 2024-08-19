public class Task {
    private String taskDescription;
    private Boolean isDone;

    public Task(String taskDescription, Boolean isDone) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
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


    public String getStatusIcon() {
        if (this.isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getTaskDescription();
    }
}
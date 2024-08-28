package tasks;

public abstract class Task {
    private String taskName;
    private boolean taskDone;
    private TaskTypes taskType;

    public TaskTypes getTaskType() {
        return taskType;
    }
    protected void setTaskType(TaskTypes taskType) {
        this.taskType = taskType;
    }
    public Task(String taskName, boolean taskDone) {
        this.taskName = taskName;
        this.taskDone = taskDone;
    }
    public void setTaskDone(boolean taskDone) {
        this.taskDone = taskDone;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public boolean isTaskDone() {
        return taskDone;
    }
    public abstract String getTaskTypeSymbol();
    public String getTaskDoneCheckmark() {
        if (this.taskDone) {
            return "X";
        } else {
            return " ";
        }
    }
    public String getTaskName() {
        return taskName;
    }
    @Override
    public String toString() {
        return String.format("Name: %s, Completed: %s", this.taskName, this.taskDone);
    }
    public String serialiaze() {
        return String.format("%s:%s:%s",
                taskType,
                taskName.replaceAll(":", "/colon"),
                taskDone
        );
    }
}


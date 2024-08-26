public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public Task() {
        this.taskName = null;
        this.isDone = false;
    }

    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public boolean completeTask() {
        isDone = true;
        return isDone;
    }

    public boolean uncompleteTask() {
        isDone = false;
        return isDone;
    }
    public String getTaskName() {
        return taskName;
    }

    public boolean getTaskStatus() {
        return isDone;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskStatus(boolean bool) {
        isDone = bool;
    }

    @Override
    public String toString() {
        String res;
        if (isDone) {
            res = "[X] ";
        }
        else {
            res = "[ ] ";
        }
        return res + taskName;
    }
}

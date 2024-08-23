public class Task {
    private String task;
    private boolean doneFlag;

    public Task(String task) {
        this.task = task;
        this.doneFlag = false;
    }

    public String taskString() {
        return this.task;
    }

    public boolean taskIsDone() {
        return this.doneFlag;
    }

    public String statusWhenDone() {
        return "[X] ";
    }

    public String statusWhenNotDone() {
        return "[ ] ";
    }

    public void markTaskDone() {
        this.doneFlag = true;
    }

    public void unmarkTaskDone() {
        this.doneFlag = false;
    }

    @Override
    public String toString() {
        String typeOfTask = "[ ] ";
        if (this.doneFlag) {
            return typeOfTask + statusWhenDone() + this.task;
        } else {
            return typeOfTask + statusWhenNotDone() + this.task;
        }
    }
}

public class Task {
    private String task;
    private boolean doneFlag;

    private String statusWhenDone = "[X] ";
    private String statusWhenNotDone = "[ ] ";

    public Task(String task) {
        this.task = task;
        this.doneFlag = false;
    }

    public void markTaskDone() {
        this.doneFlag = true;
    }

    public void unmarkTaskDone() {
        this.doneFlag = false;
    }

    @Override
    public String toString() {
        if (doneFlag) {
            return statusWhenDone + this.task;
        } else {
            return statusWhenNotDone + this.task;
        }
    }
}

public class Task {

    private final String markedInfo;
    private final String unmarkedInfo;
    private boolean isDone = false;

    public Task(String taskInfo) {
        this.markedInfo = String.format("[X] %s", taskInfo);
        this.unmarkedInfo = String.format("[ ] %s", taskInfo);
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String checkIsDone() {
        if (this.isDone) {
            return this.markedInfo;
        } else {
            return this.unmarkedInfo;
        }
    }

}

public class Task {
    String task;
    boolean isMarked;

    public Task(String task, boolean isMarked) {
        this.task = task;
        this.isMarked = isMarked;
    }

    public void markTask() {
        this.isMarked = true;
    }

    public void unmarkTask() {
        this.isMarked = false;
    }

    public String printTaskOnList() {
        if (isMarked) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }

    }

    public String toFileFormat() {
        return (this.isMarked ? "1" : "0") + " | " + this.task;
    }

}

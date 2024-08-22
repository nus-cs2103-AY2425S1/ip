public class Task {
    String task;
    boolean marked;

    public Task(String task) {
        this.task = task;
        this.marked = false;
    }

    public void markTask() {
        this.marked = true;
    }

    public void unmarkTask() {
        this.marked = false;
    }

    public String printTaskOnList() {
        if (marked) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }

    }
}

public class Task {
    private String task;
    private boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean  isDone() {
        return (done);
    }

    @Override
    public String toString() {
        String status = this.done ? "X" : " ";
        return ("[" + status + "] " + this.task);
    }
}
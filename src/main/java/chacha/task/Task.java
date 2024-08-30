package chacha.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task markDone() {
        this.isDone = true;
        return this;
    }

    public Task markUndone() {
        this.isDone = false;
        return this;
    }

    public String printTask() {
        String output = "";
        String status = (this.isDone ? "X" : " ");
        return "[" + status + "] " + this.description;
    }

    public String writeTask() {
        return "|";
    }
}

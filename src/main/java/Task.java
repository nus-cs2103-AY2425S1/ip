import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDate date;
    protected String startTime;
    protected String endTime;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.date = null;
        this.startTime = null;
        this.endTime = null;
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
}

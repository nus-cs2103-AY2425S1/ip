import java.text.MessageFormat;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    public Task unmarkAsDone() {
        this.isDone = false;
        return this;
    }

    public String getListing(){
        return MessageFormat.format("[{0}] {1}", this.getStatusIcon(), this.description);
    }

    public String getMarkAction() {
        String listing = this.getListing();
        if (this.isDone) {
            return "Okiee! I've marked this task as done: \n    " + listing;
        } else {
            return "Okiee! I've marked this task as not done yet: \n    " + listing;
        }
    }

}

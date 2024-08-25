import java.text.MessageFormat;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    protected String getDescription() {
        return this.description;
    }

    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    public Task unmarkAsDone() {
        this.isDone = false;
        return this;
    }

    public String toString(){
        return MessageFormat.format("[{0}] {1}", this.getStatusIcon(), this.getDescription());
    };

}

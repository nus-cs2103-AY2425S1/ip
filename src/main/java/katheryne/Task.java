package katheryne;

/**
 * Task Class is to manage the status of a task and
 * its description as given by the user.
 */
public class Task {
    protected String type;
    protected String description;
    protected boolean isDone;

    public Task(String content) {
        this.description = content;
        this.isDone = false;
        this.type = "";
    }

    public Task(String content, String type) {
        this.description = content;
        this.isDone = false;
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String toString() {
        if (isDone) {
            return "[" + this.getStatusIcon() + "] " + this.getDescription();
        } else {
            return "[" + this.getStatusIcon() + "] " + this.getDescription();
        }
    }

    public String toSaveString() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    public String getType() {
        return this.type;
    }

    public boolean equals(Task t) {
        return this.getType().equals(t.getType());
    }

}

package NextGPT.task;
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "";
    }
    public String getDescription() {
        return this.description;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isDone(){return this.isDone;}

    public String toString() {
        return "[" + (isDone? "X":" ") + "] " + this.description;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Task) {
            return ((Task) o).description.equals(this.description) &&
                    ((Task) o).isDone == (this.isDone);
        }
        return false;
    }
}

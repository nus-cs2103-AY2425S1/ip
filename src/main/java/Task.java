public class Task {
    private String description;
    private boolean isDone;

    Task() {
        this("null task");
    }
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDesc() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void done() {
        this.isDone = true;
    }

    public void undone() {
        this.isDone = false;
    }

    public String getStatus() {
        if (this.isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public String toString() {
        return this.getStatus() + " " + this.getDesc();
    }

}

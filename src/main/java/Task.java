public abstract class Task {
    private String description;
    private boolean isDone;

    Task(String description) {
        this(description, false);
    }

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
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

    public abstract String getType();

    public String toTsv() {
        return isDone + "\t" + description;
    };

    public String toString() {
        return this.getStatus() + " " + this.getDesc();
    }

}

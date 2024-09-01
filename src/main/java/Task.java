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
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void done() {
        isDone = true;
    }

    public void undone() {
        isDone = false;
    }

    public String getStatus() {
        if (isDone()) {
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
        return getStatus() + " " + getDesc();
    }

}

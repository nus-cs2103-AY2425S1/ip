package Gary.task;

public abstract class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public void editStatus() {
        this.isDone = !this.isDone;
    }

    public abstract String parseToFile();

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.description;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Task) {
            return ((Task) o).description.equals(this.description) &&
                    (((Task) o).isDone == this.isDone);
        }
        return false;
    }

    public String getDescription() {
        return this.description;
    }
}

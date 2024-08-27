public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, String done) {
        this.description = description;
        if (done.equals("1")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void toggleIsDone() {
        this.isDone = !this.isDone;
    }

    @Override
    public String toString() {
        return "[" + this.getType() + "]" + this.getStatusIcon() + " " + this.description;
    }

    public abstract String getType();

    public String getData() {
        int isDoneData = this.isDone ? 1 : 0;
        return this.getType() + "," + isDoneData + "," + this.description;
    }
}

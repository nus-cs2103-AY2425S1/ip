public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        String str = this.isDone ? "[X]" : "[ ]";
        str += " " + this.description;
        return str;
    }
}

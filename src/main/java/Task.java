public class Task {

    protected boolean isDone;
    protected String description;

    public Task(String name){
        this.description = name;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.description;
    }

    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsUndone() {
        this.isDone = false;
    }
}

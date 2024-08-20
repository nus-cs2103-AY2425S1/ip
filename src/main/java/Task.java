public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markUnDone() {
        this.isDone = false;
    }

    public String getName() {
        return name;
    }
    public String describe() {
        String s = String.format("[%s] %s",this.getStatusIcon(),this.getName());
        return s;
    }
}
